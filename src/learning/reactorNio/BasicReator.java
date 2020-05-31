package learning.reactorNio;

import learning.nioServer.handler.MessageHandler;
import learning.nioServer.handler.impl.PrintMessageHandlerImpl;
import learning.nioServer.register.ReciveRegister;
import learning.nioServer.register.impl.HLRegisterImpl;
import nioDemo.Handler;

import java.net.ServerSocket;
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
                ReciveRegister reciveRegister = new HLRegisterImpl(4, messageHandler);
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
        int state = READING;
        ReciveRegister reciveRegister;

        Handler(SocketChannel channel, SelectionKey key, ReciveRegister reciveRegister) {
            this.socketChannel = channel;
            this.key = key;
            this.reciveRegister = reciveRegister;
        }

        @Override
        public void run() {
            try {
                if (key.isReadable()) {
                    read();
                }
                //TO DO ：write() method
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void read() throws Exception {
            reciveRegister.doRecive(socketChannel);
//            state = WRITING;
//            key.interestOps(SelectionKey.OP_WRITE);
        }

        private void write() {
            state = READING;
            key.interestOps(SelectionKey.OP_READ);
        }
    }

}
