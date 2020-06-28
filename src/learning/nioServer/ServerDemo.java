package learning.nioServer;

import learning.nioServer.handler.MessageHandler;
import learning.nioServer.handler.impl.PrintMessageHandlerImpl;
import learning.nioServer.register.ReciveRegister;
import learning.nioServer.register.impl.HLRegisterImpl;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        NioSever server = new NioSever(8000, NioSever.ReciveRegisterType.HL);
        server.start();
    }

}
