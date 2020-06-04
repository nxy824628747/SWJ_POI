package learning.nioServer;

import learning.nioServer.util.Util;

import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

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
            for (int i = 0; i < 20; i++) {
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
        for (int i0 = 0; i0 < 20; i0++) {
            System.out.println(length);
            OutputStream outputStream = socket.getOutputStream();
            String lenStr16 = Util.numToHex16(length);
            outputStream.write(Util.hexTobytes(lenStr16));
            Thread.sleep(100);
            outputStream.write(messageBytes);
            outputStream.flush();
            BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
            byte[] buffer = new byte[100];
            int len = 0;
            StringBuilder sb = new StringBuilder();
            while ((len = in.read(buffer)) != -1) {
                //重要！为了复用 buffer，我们必须知道哪些是新写入的数据，哪些是已经处理过的数据
                String data = new String(buffer, 0, len);
                sb.append(data);
                //必须有自己的定界方式。否则只要服务端不关闭连接，len 永远不会是 -1，必须用特定的方式标识某次读取已结束！
                if (data != null && data.contains("&/n/*")) {
                    break;
                }
            }
            System.out.println(sb.substring(0));
        }
    }

    private static void printArr(byte[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }

}
