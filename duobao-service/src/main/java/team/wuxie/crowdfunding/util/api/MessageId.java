package team.wuxie.crowdfunding.util.api;

/**
 * <p>
 * Api message id
 * </p>
 *
 * @author wushige
 * @date 2016-06-30 12:47
 */
public enum MessageId {

    BAD_REQUEST(400),
    AUTH(403)
    ;

    private int code;

    MessageId(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
