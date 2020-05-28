package learning.nioServer.handler;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface MessageHandler {
    public void doHandler(SocketChannel socketChannel,ByteBuffer messageBuffer) throws Exception;
}
