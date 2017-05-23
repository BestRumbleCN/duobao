package team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto;

/**
 * ClassName:OrderQueryResp <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年12月28日 下午9:52:58
 * @see
 */
public class OrderQueryResp {
	private String return_code;
	private String return_msg;

	public String getReturn_code() {
		return return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String toResultStr() {
		String result = "<xml><return_code>" + this.getReturn_code() + "</return_code>";
		if (!this.getReturn_code().equals("SUCCESS")) {
			result += "<return_msg>" + this.getReturn_msg() + "</return_msg>";
		}
		result += "</xml>";
		return result;
	}
}
