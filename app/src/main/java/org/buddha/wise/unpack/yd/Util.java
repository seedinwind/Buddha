package org.buddha.wise.unpack.yd;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by Yuan Jiwei on 17/3/24.
 */

public class Util {
    public static String createGuest() {
        return "HG_" + UUID.randomUUID().toString();
    }

    public static String createCoded(String s, String username) {

        String result = null;
        try {
            byte[] temp = MessageDigest.getInstance("MD5").digest(username.getBytes());
            result = s + b(temp);
        } catch (NoSuchAlgorithmException e) {

        }

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("sha-1");
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        for (int i = 0; i < 1000; i++) {
            byte[] arrayOfByte = digest.digest(result.getBytes());
            result = b(arrayOfByte);
        }
        return result;

    }


    public static String b(byte[] paramArrayOfByte) {
        String str1 = "";
        int i = 0;
        while (i < paramArrayOfByte.length) {
            String str3 = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
            String str2 = str3;
            if (str3.length() == 1) {
                str2 = '0' + str3;
            }
            str1 = str1 + str2;
            i += 1;
        }
        return str1;
    }
}
