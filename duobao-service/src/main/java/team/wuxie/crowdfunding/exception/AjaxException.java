package team.wuxie.crowdfunding.exception;

/**
 * <p>
 * Ajax请求的controller异常
 * </p>
 *
 * @author wushige
 * @date 2016-06-30 11:59
 */
public class AjaxException extends RuntimeException {

    public AjaxException() {
    }

    public AjaxException(String message) {
        super(message);
    }

    public AjaxException(String message, Throwable cause) {
        super(message, cause);
    }

    public AjaxException(Throwable cause) {
        super(cause);
    }

    public AjaxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
