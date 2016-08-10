package team.wuxie.crowdfunding.util.encrypt;

import java.util.Formatter;

/**
 * <p>
 * 二进制转十进制
 * </p>
 *
 * @author wushige
 * @date 2016-06-07 11:39
 */
public class Byte2Hex {
    public Byte2Hex() {
    }

    public static String byte2Hex(byte b) {
        String hex = Integer.toHexString(b);
        if(hex.length() > 2) {
            hex = hex.substring(hex.length() - 2);
        }

        while(hex.length() < 2) {
            hex = "0" + hex;
        }

        return hex;
    }

    public static String byte2Hex(byte[] bytes) {
        Formatter formatter = new Formatter();
        byte[] hash = bytes;
        int len$ = bytes.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            byte b = hash[i$];
            formatter.format("%02x", new Object[]{Byte.valueOf(b)});
        }

        String var6 = formatter.toString();
        formatter.close();
        return var6;
    }
}
