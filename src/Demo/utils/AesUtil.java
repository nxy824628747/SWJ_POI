package Demo.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/** @author shen_feng 加密解密 */
public class AesUtil {
  public static String encrypt(String sSrc, String sKey) throws Exception {
    if (sKey == null) {
      System.out.print("Key为空null");
      return null;
    }
    if (sKey.length() != 16) {
      System.out.print("Key长度不是16位");
      return null;
    }
    byte[] raw = sKey.getBytes("utf-8");
    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // "算法/模式/补码方式"
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
    byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

    return new Base64().encodeToString(encrypted);
  }

  // 解密
  public static String decrypt(String sSrc, String sKey) {
    try {
      if (sKey == null) {
        System.out.print("Key为空null");
        return null;
      }
      if (sKey.length() != 16) {
        System.out.print("Key长度不是16位");
        return null;
      }
      byte[] raw = sKey.getBytes("utf-8");
      SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, skeySpec);
      byte[] encrypted1 = new Base64().decode(sSrc); // 先用base64解密
      byte[] original = cipher.doFinal(encrypted1);
      String originalString = new String(original, "utf-8");
      return originalString;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 进行UTF-8编码
   *
   * @param
   * @return
   */
  public static String StringToUTF8(String str) {
    String xmString = "";
    String xmlUTF8 = "";
    try {
      xmString = new String(str.getBytes("UTF-8"));
      xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return xmlUTF8;
  }
}
