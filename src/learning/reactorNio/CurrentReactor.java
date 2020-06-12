package learning.reactorNio;

import learning.nioServer.handler.MessageHandler;
import learning.nioServer.handler.impl.PrintMessageHandlerImpl;
import learning.nioServer.register.ReciveRegister;
import learning.nioServer.register.impl.HLRegisterImpl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Niuxy
 * @Date 2020/6/10 9:42 下午
 * @Description 多 selector 多线程的 NIO 服务端
 * 使用 NIO 时一定要摒弃 BIO 的阻塞思维，我们的代码面向的是事件，而不是连接
 * 至于多次事件完成一个连接的情况，我们可以通过 attachment 记录该连接上次事件处理的结果。
 * 上面做法的前提是一个连接只允许注册一个感兴趣的事件。
 */
public class CurrentReactor implements Runnable {
    // CPU 核心数
    int cpuNums = Runtime.getRuntime().availableProcessors() + 3;
    // selector 数
    int selectorNums;
    // 监听读写事件的循环
    Selector[] selectorArr;
    // 事件处理线程池
    ExecutorService executorService;
    ServerSocketChannel serverSocketChannel;
    //当前使用的 selector 坐标
    Integer currentSelector;


    CurrentReactor(ServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
        //读写事件从 1 开始使用，第一个 selector 用于监听连接事件
        currentSelector = 1;
        try {
            this.selectorNums = 3;
            selectorArr = new Selector[3];
            // 四核以上服务器较好
            executorService = Executors.newFixedThreadPool(cpuNums + selectorNums);
            for (int i = 0; i < selectorNums; i++) {
                selectorArr[i] = SelectorProvider.provider().openSelector();
            }
            // 注册 server 连接事件
            SelectionKey key = serverSocketChannel.register(selectorArr[0], SelectionKey.OP_ACCEPT);
            key.attach(new Acceptor());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //事件分发器
    private void dispatch(SelectionKey key) {
        if (key == null || key.attachment() == null) {
            return;
        }
        try {
            executorService.execute((Runnable) key.attachment());
        } catch (Exception e) {
            //任务提交异常则什么都不做，因为 NIO 的水平触发机制会继续触发事件
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            Selector selector = selectorArr[i];
            executorService.execute(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                selector.select(100);
                                Set<SelectionKey> keys = selector.selectedKeys();
                                Iterator iterator = keys.iterator();
                                while (iterator.hasNext()) {
                                    SelectionKey key = (SelectionKey) iterator.next();
                                    iterator.remove();
                                    dispatch(key);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
        }
    }

    /**
     * @Author Niuxy
     * @Date 2020/6/10 9:01 下午
     * @Description 连接事件处理器
     */
    class Acceptor implements Runnable {
        Selector handlerSelector;

        Acceptor() {
            // 采用轮询的负载均衡策略选取 selector
            synchronized (currentSelector) {
                if (currentSelector == 3) {
                    currentSelector = 1;
                }
                this.handlerSelector = selectorArr[currentSelector];
                currentSelector++;
            }
        }

        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel == null) {
                    return;
                }
                socketChannel.configureBlocking(false);
                SelectionKey key = socketChannel.register(this.handlerSelector, SelectionKey.OP_READ);
                //报文接收策略，与连接一一绑定
                MessageHandler messageHandler = new PrintMessageHandlerImpl();
                // register 与 select 方法竞争锁，防止 register 被 select 阻塞
                this.handlerSelector.wakeup();
                ReciveRegister reciveRegister = new HLRegisterImpl(2, messageHandler);
                //注册 key 的同时 将事件处理的 "回调" 函数绑定到 key 上
                key.attach(new Handler(socketChannel, key, reciveRegister));
            } catch (ClosedChannelException ce) {
                ce.printStackTrace();
                //to do
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Author Niuxy
     * @Date 2020/6/10 8:50 下午
     * @Description 就绪读写事件处理器。粗暴的将方法全部加锁，一个连接不应该有多个线程同时处理，但
     * Reactor 模式下不同的事件提交线程池后可能造成多个线程处理同一个链接
     * 不能依靠 key 的 Readable 或 Writeable 状态决定当前是读是写，读写应当由完整的请求进行分割，一读一写，再处理下次请求
     * 因此需要自己维护读写状态位
     */
    class Handler implements Runnable {
        public static final int READING = 0, WRITING = 1;
        volatile SocketChannel socketChannel;
        volatile SelectionKey key;
        /**
         * @Author Niuxy
         * @Date 2020/6/2 9:29 下午
         * @Description 在响应上一个请求前，我们不希望处理下一个请求，因此在 Handler 维护一个状态位，标识目前应当
         * 处理读事件还是写事件
         * 我们必须保证接收和回复的顺序性，保证客户端可以对响应做出正确的处理
         * 当然也有其它的处理方式，我们将响应数据装入一个有序队列，并顺序的处理这些响应。或者通过令牌将请求和响应
         * 进行对应。
         */
        int state = READING;
        ReciveRegister reciveRegister;
        String readResult = null;
        ByteBuffer writeBuffer = null;

        Handler(SocketChannel channel, SelectionKey key, ReciveRegister reciveRegister) {
            /**
             * @Author Niuxy
             * @Date 2020/6/4 9:39 下午
             * @Description 重要！必须保证构造方法与其它方法的互斥
             *              否则可能造成构造方法没有执行完，其它线程已开始执行该对象的其它方法
             *              场景不容易复现，但在大剂量请求到达时 Reactor 模式中时很容易出现
             */
            synchronized (this) {
                this.socketChannel = channel;
                this.key = key;
                this.reciveRegister = reciveRegister;
            }
        }

        @Override
        public synchronized void run() {
            try {
                if (state == READING) {
                    read();
                } else {
                    write();
                }
            } catch (RuntimeException rex) {
                throw rex;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private synchronized void read() throws Exception {
            String re = reciveRegister.doRecive(socketChannel);
            if (re != null && re != "") {
                readResult = re;
                state = WRITING;
                key.interestOps(SelectionKey.OP_WRITE);
            }
        }

        private synchronized void write() throws IOException {
            if (this.readResult == null || readResult == "") {
                return;
            }
            //如果不是第一次触发写事件，接着上次的写
            if (writeBuffer == null) {
                writeBuffer = ByteBuffer.wrap(this.readResult.getBytes());
            }

            //该循环处理发送缓冲区处理速度小于网卡发送速度，无法一次性将 buffer 中数据写入发送缓冲区的情况
            socketChannel.write(writeBuffer);
            if (writeBuffer.position() != writeBuffer.limit()) {
                return;
            }
            writeBuffer = null;
            readResult = null;
            state = READING;
            //写完将兴趣移除，否则会将 CPU 跑满
            key.interestOps(SelectionKey.OP_READ);
        }
    }
}
