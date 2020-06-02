package learning.reactorNio;

import learning.nioServer.handler.MessageHandler;
import learning.nioServer.handler.impl.PrintMessageHandlerImpl;
import learning.nioServer.register.ReciveRegister;
import learning.nioServer.register.impl.HLRegisterImpl;
import nioDemo.Handler;

import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author Niuxy
 * @Date 2020/5/31 8:15 下午
 * @Description 最基础的 reactor 模型
 * 本模型不负责 serverSocket 与 selector 的初始化
 * 入口处的并发需要通过负载策略将 socketChannel 的事件注册到不同的 selector 上，待验证
 * selector 底层使用的是 OS 提供给我们的事件通知器
 */
public class BasicReator implements Runnable {
    ServerSocketChannel serverSocketChannel;
    Selector selector;

    public BasicReator(ServerSocketChannel serverSocketChannel, Selector selector)
            throws ClosedChannelException {
        this.serverSocketChannel = serverSocketChannel;
        this.selector = selector;
        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        key.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                selector.select();
                Set keySet = selector.selectedKeys();
                Iterator iterator = keySet.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = (SelectionKey) iterator.next();
                    iterator.remove();
                    dispatch(key);
                }
            }
        } catch (RuntimeException re) {
            // 接收策略层面没有正确解析报文会抛出 RuntimeException，为了测试，遇到该异常立即终止
            throw re;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author Niuxy
     * @Date 2020/5/31 7:23 下午
     * @Description 核心的部分
     * 无论什么事件被触发，我们均执行与事件绑定的方法
     * 在注册感兴趣的事件时，就将处理该事同步件的方法绑定到了对应的 selectionKey 中
     */
    private void dispatch(SelectionKey key) {
        Runnable att = (Runnable) key.attachment();
        try {
            if (att == null) {
                return;
            }
            att.run();
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            //dispatch 层面面向唯一连接，具有处理异常的能力，捕获处理不影响其它连接
            //TO DO
            e.printStackTrace();
        }
    }

    /**
     * @Author Niuxy
     * @Date 2020/5/31 7:41 下午
     * @Description 处理连接请求，注册读写事件并绑定读写事件处理器
     */
    class Acceptor implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel == null) {
                    return;
                }
                socketChannel.configureBlocking(false);
                SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);
                //报文接收策略，与连接一一绑定
                MessageHandler messageHandler = new PrintMessageHandlerImpl();
                ReciveRegister reciveRegister = new HLRegisterImpl(2, messageHandler);
                //注册 key 的同时 将事件处理的 "回调" 函数绑定到 key 上
                key.attach(new Handler(socketChannel, key, reciveRegister));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Author Niuxy
     * @Date 2020/5/31 11:44 下午
     * @Description 读写处理器
     */
    class Handler implements Runnable {
        public static final int READING = 0, WRITING = 1;
        SocketChannel socketChannel;
        SelectionKey key;
        /**
         * @Author Niuxy
         * @Date 2020/6/2 9:29 下午
         * @Description
         * 在响应上一个请求前，我们不希望处理下一个请求，因此在 Handler 维护一个状态位，标识目前应当
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
            this.socketChannel = channel;
            this.key = key;
            this.reciveRegister = reciveRegister;
        }

        @Override
        public void run() {
            try {
                if (state == READING) {
                    read();
                } else {
                    write();
                }
                //TO DO ：write() method
            } catch (RuntimeException rex) {
                throw rex;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void read() throws Exception {
            String re = reciveRegister.doRecive(socketChannel);
            if (re != null && re != "") {
                readResult = re;
                state = WRITING;
                key.interestOps(SelectionKey.OP_WRITE);
            }
        }

        private void write() throws IOException {
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
            //写完将兴趣移除，否则会将 CPU 跑满
            key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
            writeBuffer = null;
            readResult = null;
            state = READING;
            key.interestOps(SelectionKey.OP_READ);
        }
    }

}
