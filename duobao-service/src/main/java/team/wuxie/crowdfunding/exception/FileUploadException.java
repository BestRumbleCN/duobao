package team.wuxie.crowdfunding.exception;

/**
 * <p>
 * 文件上传异常
 * </p>
 *
 * @author wushige
 * @date 2016-08-19 17:03
 */
public class FileUploadException extends RuntimeException {

    public FileUploadException() {
        super();
    }

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadException(Throwable cause) {
        super(cause);
    }

    public FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
