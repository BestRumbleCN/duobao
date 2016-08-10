package team.wuxie.crowdfunding.util.encrypt;

/**
 * <p>
 * 加密算法
 * </p>
 *
 * @author wushige
 * @date 2016-06-07 11:37
 */
public enum Algorithm {
    MD5("MD5", "md5 encrypt"),
    SHA("SHA", "has encrypt"),
    AES("AES", "aes encrypt");

    private final String key;
    private final String desc;

    private Algorithm(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return this.key;
    }

    public String getDesc() {
        return this.desc;
    }
}
