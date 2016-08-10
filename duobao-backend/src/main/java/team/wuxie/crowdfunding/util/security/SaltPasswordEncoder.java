package team.wuxie.crowdfunding.util.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import team.wuxie.crowdfunding.util.encrypt.Algorithm;
import team.wuxie.crowdfunding.util.encrypt.Byte2Hex;

import java.security.MessageDigest;

/**
 * <p>
 * 自定义密码加密工具
 * </p>
 *
 * @author wushige
 * @date 2016-08-09 10:43
 */
public class SaltPasswordEncoder implements PasswordEncoder {

    private static final Logger LOGGER = LoggerFactory.getLogger("SaltPasswordEncoder");

    private String salt;
    private String algorithm;

    public SaltPasswordEncoder() {
    }

    public SaltPasswordEncoder(String salt, Algorithm algorithm) {
        this.salt = salt;
        this.algorithm = algorithm.getKey();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return (new SaltPasswordEncoder(rawPassword.toString(), Algorithm.MD5).encodeText(rawPassword.toString()));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return (new SaltPasswordEncoder(rawPassword.toString(), Algorithm.MD5).isValid(encodedPassword, rawPassword.toString()));
    }

    public String encodeText(String rawText) {
        try {
            MessageDigest e = MessageDigest.getInstance(this.algorithm);
            return Byte2Hex.byte2Hex(e.digest(this.mergeRawTextAndSalt(rawText).getBytes("utf-8")));
        } catch (Exception var3) {
            LOGGER.info("SaltPasswordEncoder encodeText exception.");
            var3.printStackTrace();
            return null;
        }
    }

    public boolean isValid(String encodeText, String rawText) {
        return this.encodeText(rawText).equals(encodeText);
    }

    private String mergeRawTextAndSalt(String rawText) {
        if(rawText == null) {
            rawText = "";
        }

        if(this.salt != null && !"".equals(this.salt)) {
            return rawText + "#" + this.salt;
        } else {
            return rawText;
        }
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public static void main(String[] args) {
        System.out.println(new SaltPasswordEncoder().encode("admin"));
        System.out.println(new SaltPasswordEncoder().encode("admin"));
        System.out.println(new SaltPasswordEncoder().encode("admin"));
        System.out.println(new SaltPasswordEncoder().encode("admin"));
    }
}
