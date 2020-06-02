package learning.nioServer.register;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author Niuxy
 * @Date 2020/5/28 8:35 下午
 * @Description 数据接收策略
 */
public interface ReciveRegister {
    public String doRecive(SocketChannel socketChannel) throws Exception;
}
