package learning.nioServer;

import learning.nioServer.handler.MessageHandler;
import learning.nioServer.handler.impl.PrintMessageHandlerImpl;
import learning.nioServer.register.ReciveRegister;
import learning.nioServer.register.impl.HLRegisterImpl;

import java.io.IOException;

public class ServerDemo {
    public static final NioSever SERVER = new NioSever(8000, NioSever.ReciveRegisterType.HL);

    public static void main(String[] args) throws IOException {
        NioSever SERVER = SERVER;
        SERVER.start();
    }
}
