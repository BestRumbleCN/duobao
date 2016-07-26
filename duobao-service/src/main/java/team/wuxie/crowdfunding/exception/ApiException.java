package team.wuxie.crowdfunding.exception;

/**
 * <p>
 * 接口请求的controller异常
 * </p>
 *
 * @author wushige
 * @date 2016-06-30 12:00
 */
public class ApiException extends RuntimeException {

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
