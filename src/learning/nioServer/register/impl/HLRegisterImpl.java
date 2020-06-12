package learning.nioServer.register.impl;

import learning.nioServer.handler.MessageHandler;
import learning.nioServer.register.ReciveRegister;
import learning.nioServer.util.Util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author Niuxy
 * @Date 2020/5/28 8:36 下午
 * @Description 报文头标示报文长度的定界策略，由于存在类成员缓冲区的原因需要与连接一一绑定
 */
public class HLRegisterImpl implements ReciveRegister {
    //报文头长度
    private int headLength = 0;
    //报文内容长度
    private int messageLength = 0;

    private MessageHandler messageHandler;

    boolean isCache = false;
    ByteBuffer headCacheBuffer;
    ByteBuffer messageCacheBuffer;

    public HLRegisterImpl(int headLength, MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
        this.headLength = headLength;
        headCacheBuffer = ByteBuffer.allocate(headLength);
    }

    @Override
    public String doRecive(SocketChannel socketChannel) throws Exception {
        //判断是否已读取报文头
        if (messageLength == 0) {
            int readLen = socketChannel.read(headCacheBuffer);
            if (Util.isFullBuffer(headCacheBuffer)) {
                headCacheBuffer.flip();
                String messageLenthStr = Util.bytes2HexString(headCacheBuffer.array());
//                messageLength = headCacheBuffer.getInt();
                messageLength = Integer.parseInt(messageLenthStr, 16);
                System.out.println(messageLength);
                messageCacheBuffer = ByteBuffer.allocate(messageLength);
                headCacheBuffer.clear();
                return null;
            }
        } else {
            int readLen = socketChannel.read(messageCacheBuffer);
            String re = "";
            if (Util.isFullBuffer(messageCacheBuffer)) {
                re = messageHandler.doHandler(socketChannel, messageCacheBuffer);
                messageLength = 0;
                headLength = 0;
                messageCacheBuffer = null;
                return re;
            }
            return null;
        }
        return null;
    }

}
