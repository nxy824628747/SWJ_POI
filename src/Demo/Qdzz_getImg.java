package Demo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Qdzz_getImg {

    public static void m1ain(String[] args) {
        executeImgByUrlMD("http://59.224.6.5:8090", "/main/api/getLicenseFileBase",
                "11370600004260360F-8B888CA5FE4A4A5FAEF1A2C28ADC0951-20190819154329-1_11370600004260360F_201909060004_1",
                "YTSDZZZYDD", "457F637B8FFF45C3A16DA5634A34C0F4");

    }



    public static final void executeImgByUrlMD(String ipAndPort, String apiPath, String licenseNo, String appCode, String accessToken) {
        try {
            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            try {
                StringBuffer sb = new StringBuffer(ipAndPort);
                sb.append("/");
                sb.append(apiPath);
                sb.append("?");
                sb.append(setParam(licenseNo, appCode, accessToken));
                HttpGet httpGet = new HttpGet(sb.toString());
                client = HttpClients.createDefault();
                response = client.execute(httpGet);
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                System.out.println(result);

            } finally {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static final String setParam(String licenseNo, String appCode, String accessToken) {
        StringBuffer param = new StringBuffer("licenseNo=");
        param.append(licenseNo);
        param.append("&appCode=");
        param.append(appCode);
        param.append("&time=");
        param.append(System.currentTimeMillis());
        param.append("&accessToken=");
        param.append(accessToken);
        String sign = toMD5(param.toString());
        param.append("&sign=");
        param.append(sign);
        System.out.print(param.toString());
        return param.toString();
    }

    public final static String toMD5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte b[] = md5.digest();
            StringBuffer sb = new StringBuffer("");
            for (int n = 0; n < b.length; n++) {
                int i = b[n];
                if (i < 0) i += 256;
                if (i < 16) sb.append("0");
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();  //32位加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }



}
