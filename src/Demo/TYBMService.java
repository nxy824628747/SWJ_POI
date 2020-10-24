package Demo;


import org.apache.commons.codec.digest.DigestUtils;
import sun.security.provider.MD5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TYBMService {

    public static void main(String[] args) throws Exception{
        String src = "INSPUR-DZZW-ZHSL"+"143153"+"789";
        String token = md5(src);
        System.out.println(DigestUtils.md5Hex(src));
    }

    public static String md5(String param) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(param.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }
}
