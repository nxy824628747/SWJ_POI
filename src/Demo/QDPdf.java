package Demo;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class QDPdf {
    public static void main(String[] args) throws Exception{
        //待签章文件路径
        String sourceFilePath = "C:\\Applications\\applications\\SWJ_POI\\111\\证照0_青岛地铁集团有限公司-建筑工程施工许可证（新）.ofd";
        //签章接口返回接口文件保存路径
        String signedFilePath = "C:\\Applications\\applications\\SWJ_POI\\111\\111.ofd";
        //签章规则号
        String ruleNum = "6D91478DF57AE265";
        //签章类型----按北京ca接口标准传，201为pdf，202为ofd
        String type = "202";
        QDTools tools=new QDTools();
        byte[] result = tools.execute(sourceFilePath, type,ruleNum);
        if(result != null){
            System.out.println("333");
            FileUtils.writeByteArrayToFile(new File(signedFilePath), result);
        }else {
            System.out.println("444");
        }
    }
}
