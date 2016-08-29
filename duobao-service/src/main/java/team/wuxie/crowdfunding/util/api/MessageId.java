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
    AUTH(403),
    LOGIN(1000),
    SEND_SMS(1001),
    REGISTER(1002),
    LOGOUT(1003),
    FORGOT_PASSWORD(1004),
    GET_PROFILE(1005),
    GET_OTHER_PROFILE(1006),
    UPDATE_PROFILE(1007),
    UPDATE_PASSWORD(1008)
    ;

    private int code;

    MessageId(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
