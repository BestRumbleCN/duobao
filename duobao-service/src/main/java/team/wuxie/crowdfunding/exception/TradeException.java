package team.wuxie.crowdfunding.exception;
/**
 * ClassName:TradeException <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年12月28日 下午6:58:52
 * @see 	 
 */
public class TradeException extends RuntimeException {
	
	private static final long serialVersionUID = 8472104175535714603L;

	public TradeException() {
        super();
    }

    public TradeException(String message) {
        super(message);
    }

    public TradeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TradeException(Throwable cause) {
        super(cause);
    }

    public TradeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
