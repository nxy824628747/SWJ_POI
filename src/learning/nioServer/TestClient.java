package learning.nioServer;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class TestClient {
    public static void main(String[] args) throws Exception {
        final String msg = "hellow server!hellow server!hellow server!hellow server!hellow server!hellow server!hellow server!hellowhellow server!hellow server!hellow server!hellow server!hellow server!hellow server!hellow";
        Thread thread0 = new Thread(() -> {
            sendMsg(msg);
        });
        Thread thread1 = new Thread(() -> {
            sendMsg(msg);
        });
        Thread thread2 = new Thread(() -> {
            sendMsg(msg);
        });
        Thread thread3 = new Thread(() -> {
            sendMsg(msg);
        });
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();

    }

    private static void sendMsg(String msg) {
        try {
            for (int i = 0; i < 10; i++) {
                send(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void send(String message) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8000);
        byte[] messageBytes = message.getBytes();
        Integer length = messageBytes.length;
        System.out.println(length);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(ByteBuffer.allocate(4).putInt(length).array());
        Thread.sleep(100);
        outputStream.write(messageBytes);
        outputStream.flush();
        outputStream.close();
    }

}
