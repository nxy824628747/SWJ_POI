package Demo;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class QDTools {

    public byte[] execute(String pathName, String type,String rullNum) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost sign = new HttpPost("http://15.72.168.100/anysign-server-sign/services/pdfServer");
        byte[] pdfBty = FileUtils.readFileToByteArray(new File(pathName));
        String headStr = "{\"type\":\"" + type + "\", \"ruleNum\":\"" + rullNum + "\"}";
        byte[] dataBty = mergeData(pdfBty, headStr);
        sign.setEntity(new ByteArrayEntity(dataBty));
        CloseableHttpResponse response = httpClient.execute(sign);
        HttpEntity responseEntity = response.getEntity();
        InputStream inputStream = responseEntity.getContent();
        return dealResponse(inputStream);
    }

    //BYte数组转char数组
    public char[] getChars(byte[] bytes) {
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }
    

    private byte[] mergeData(byte[] pdfByte, String headStr){
        byte[] reqByte = headStr.getBytes();
        byte[] headLengthBty = this.intToByte(reqByte.length);
        byte[] headBty = this.byteToByte(reqByte);
        byte[] bodyLengthBty = this.intToByte(pdfByte.length);
        byte[] allBty = new byte[headLengthBty.length + headBty.length + bodyLengthBty.length + pdfByte.length];
        System.arraycopy(headLengthBty, 0, allBty, 0, headLengthBty.length);
        System.arraycopy(headBty, 0, allBty, headLengthBty.length, headBty.length);
        System.arraycopy(bodyLengthBty, 0, allBty, headLengthBty.length + headBty.length, bodyLengthBty.length);
        System.arraycopy(pdfByte, 0, allBty, headLengthBty.length + headBty.length + bodyLengthBty.length, pdfByte.length);
        return allBty;
    }

    private byte[] dealResponse(InputStream inputStream) throws IOException {
        final int CONTEXT_LENGTH_SIZE = 4;
        byte[] bb;
        int n = 0;
        int headLength = 0;
        String statusCode = "";
        // 解析消息头，得到状态 statusCode
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                bb = new byte[CONTEXT_LENGTH_SIZE];
                inputStream.read(bb, 0, CONTEXT_LENGTH_SIZE);
                n = byteToInt(bb);
                headLength = n;
            }else if(i == 1){
                bb = new byte[headLength];
                inputStream.read(bb, 0, headLength);
                statusCode = new String(bb);
                System.out.println("响应的状态码为：" + statusCode);
            }else{
                bb = new byte[CONTEXT_LENGTH_SIZE];
                inputStream.read(bb, 0, CONTEXT_LENGTH_SIZE);
                n = byteToInt(bb);
            }
        }
        // 如果 statusCode 为 200 表示操作通过,获取具体结果信息
        if ("200".equals(statusCode) && n>0) {
            byte[] result = new byte[n];
            int offset = 0;
            while (true){
                int length = inputStream.read(result, offset, n-offset);
                if(length + offset == n){
                    break;
                }
                offset = length + offset;
            }
            return result;
        }else {
            return null;
        }
    }

    private int byteToInt(byte[] inData) {
        ByteBuffer byteBuf = ByteBuffer.allocate(4);
        byteBuf.put(inData);
        return byteBuf.getInt(0);
    }

    private byte[] intToByte(int inData) {
        ByteBuffer byteBuf = ByteBuffer.allocate(4);
        byteBuf.putInt(inData);
        return byteBuf.array();
    }

    private byte[] byteToByte(byte[] headData) {
        ByteBuffer byteBuf = ByteBuffer.allocate(headData.length);
        byteBuf.put(headData);
        return  byteBuf.array();
    }

}



