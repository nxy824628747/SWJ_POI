package learning.nioServer;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class TestClient {
    public static void main(String[] args) throws Exception {
        String msg="hellow server!hellow server!hellow server!hellow server!hellow server!hellow server!hellow server!hellowhellow server!hellow server!hellow server!hellow server!hellow server!hellow server!hellow";
        send(msg);
        send(msg);
        send(msg);

    }

    public static void send(String message) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8000);
        byte[] messageBytes = message.getBytes();
        Integer length = messageBytes.length;
        System.out.println(length);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(ByteBuffer.allocate(4).putInt(length).array());
        outputStream.write(messageBytes);
        outputStream.flush();
        outputStream.close();
    }

}
