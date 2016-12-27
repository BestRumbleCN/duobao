package team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto;

/**
 * https://pay.weixin.qq.com/wiki/doc/api/app.php?chapter=9_7&index=3 不需要签名
 * 
 * @author leon
 *
 */
public class PaymentNotificationResponse implements WeixinPayPdu {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2254737846847521281L;
	// 返回状态码
	private String return_code;
	// 返回信息
	private String return_msg;
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	@Override
	public String toString() {
		return "PaymentNotificationResponse [return_code=" + return_code
				+ ", return_msg=" + return_msg + "]";
	}
}
