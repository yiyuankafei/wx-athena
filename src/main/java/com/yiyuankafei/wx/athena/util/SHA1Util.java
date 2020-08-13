package com.yiyuankafei.wx.athena.util;

import java.security.MessageDigest;

/**
 * SHA1加密，生成签名
 */
public class SHA1Util {

    public static String encode(String text) throws Exception {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
        mdTemp.update(text.getBytes("UTF-8"));
        byte[] md = mdTemp.digest();
        int j = md.length;
        char buf[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
            buf[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(buf);
    }
}
