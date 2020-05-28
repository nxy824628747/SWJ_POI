package learning.nioServer;

import learning.nioServer.handler.MessageHandler;
import learning.nioServer.handler.impl.PrintMessageHandlerImpl;
import learning.nioServer.register.ReciveRegister;
import learning.nioServer.register.impl.HLRegisterImpl;

import java.io.IOException;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        MessageHandler messageHandler = new PrintMessageHandlerImpl();
        ReciveRegister reciveRegister = new HLRegisterImpl(4, messageHandler);
        NioSever server = new NioSever(8000, reciveRegister);
        server.start();
    }
}
