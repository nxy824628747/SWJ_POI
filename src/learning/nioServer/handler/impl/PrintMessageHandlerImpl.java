package learning.nioServer.handler.impl;

import learning.nioServer.handler.MessageHandler;
import learning.nioServer.util.Util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class PrintMessageHandlerImpl implements MessageHandler {
    @Override
    public void doHandler(SocketChannel socketChannel, ByteBuffer messageBuffer) throws IOException {
        String message = Util.bufferToString(messageBuffer);
        System.out.println(message);
        socketChannel.close();
    }
}
