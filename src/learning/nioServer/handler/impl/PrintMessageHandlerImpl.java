package learning.nioServer.handler.impl;

import learning.nioServer.handler.MessageHandler;
import learning.nioServer.util.Util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class PrintMessageHandlerImpl implements MessageHandler {
    String target = "hellow server!hellow server!hellow server!hellow server!hellow server!hellow server!hellow server!hellowhellow server!hellow server!hellow server!hellow server!hellow server!hellow server!hellow";

    @Override
    public String doHandler(SocketChannel socketChannel, ByteBuffer messageBuffer) throws Exception {
        String message = new String(messageBuffer.array());
        String re = "";
        if (!target.equals(message)) {
            System.out.println("error!: " + message);
            re="error&/n/*";
        } else {
            System.out.println("success!");
            re="successsuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssucc" +
                    "esssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssuccesssucces"
                    + "successsuccesssuccesssuccesssuccesssuccesssuccesssuccessssuccesssuccesssuccesssuccesssuccesss" +
                    "uccesssuccesssuccesssuccesssuccesssuccesssuccess!&/n/*";
        }
        messageBuffer = null;
        return re;
    }
}
