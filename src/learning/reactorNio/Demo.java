package learning.reactorNio;

import learning.nioServer.handler.MessageHandler;
import learning.nioServer.handler.impl.PrintMessageHandlerImpl;
import learning.nioServer.register.ReciveRegister;
import learning.nioServer.register.impl.HLRegisterImpl;

import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
    public static void main1(String[] args) throws Exception {
        Selector selector = SelectorProvider.provider().openSelector();
        Selector handlerSelector = SelectorProvider.provider().openSelector();
        ServerSocketChannel severChannel = ServerSocketChannel.open();
        System.out.println("server start!");
        severChannel.configureBlocking(false);
        severChannel.bind(new InetSocketAddress(8000));
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        BasicReator basicReator = new BasicReator(severChannel, selector,handlerSelector,newCachedThreadPool);
        new Thread(basicReator).start();
    }

    public static void main(String[] args) throws Exception{
        ServerSocketChannel severChannel = ServerSocketChannel.open();
        System.out.println("server start!");
        severChannel.configureBlocking(false);
        severChannel.bind(new InetSocketAddress(8000));
        CurrentReactor currentReactor=new CurrentReactor(severChannel);
        currentReactor.run();
    }

}
