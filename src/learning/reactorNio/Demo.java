package learning.reactorNio;

import learning.nioServer.handler.MessageHandler;
import learning.nioServer.handler.impl.PrintMessageHandlerImpl;
import learning.nioServer.register.ReciveRegister;
import learning.nioServer.register.impl.HLRegisterImpl;

import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class Demo {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel severChannel = ServerSocketChannel.open();
        System.out.println("server start!");
        severChannel.configureBlocking(false);
        severChannel.bind(new InetSocketAddress(8000));
        BasicReator basicReator = new BasicReator(severChannel, selector);
        new Thread(basicReator).start();
    }

}
