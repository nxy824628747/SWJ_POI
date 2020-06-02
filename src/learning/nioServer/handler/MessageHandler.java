package learning.nioServer.handler;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface MessageHandler {
    public String doHandler(SocketChannel socketChannel,ByteBuffer messageBuffer) throws Exception;
}
