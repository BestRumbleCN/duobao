package team.wuxie.crowdfunding.exception;

/**
 * <p>
 * Web请求的controller异常
 * </p>
 *
 * @author wushige
 * @date 2016-06-30 11:59
 */
public class WebException extends RuntimeException {

    public WebException() {
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebException(Throwable cause) {
        super(cause);
    }

    public WebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
