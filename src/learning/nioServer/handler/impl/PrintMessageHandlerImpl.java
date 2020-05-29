package learning.nioServer.handler.impl;

import learning.nioServer.handler.MessageHandler;
import learning.nioServer.util.Util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class PrintMessageHandlerImpl implements MessageHandler {
    String target = "hellow server!hellow server!hellow server!hellow server!hellow server!hellow server!hellow server!hellowhellow server!hellow server!hellow server!hellow server!hellow server!hellow server!hellow";

    @Override
    public void doHandler(SocketChannel socketChannel, ByteBuffer messageBuffer) throws IOException {
        String message = new String(messageBuffer.array());
//        String message=Util.bufferToString(messageBuffer);
        if (!target.equals(message)) {
            System.out.println("error!: " + message);
        } else {
            System.out.println("success!");
        }
        messageBuffer = null;
    }
}
