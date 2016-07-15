package team.wuxie.crowdfunding.result;

import java.io.Serializable;

/**
 * ClassName:ApiResult <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年7月15日 下午8:49:12
 * @see 	 
 */
public class ApiResult implements Serializable{
	private static final long serialVersionUID = 8212256895272345291L;

	private int status;

	private String message;

	private Object data;

	public static ApiResult success() {
		return success(null);
	}

	public static ApiResult success(String message) {
		return success(message, null);
	}

	public static ApiResult success(String message, Object data) {
		ApiResult result = new ApiResult();
		result.setStatus(Status.SUCCESS.getValue());
		result.setMessage(message);
		result.setData(data);
		return result;
	}

	public static ApiResult failure() {
		return failure(null);
	}

	public static ApiResult failure(String message) {
		return failure(message, null);
	}

	public static ApiResult failure(String message, Object data) {
		ApiResult result = new ApiResult();
		result.setStatus(Status.FAILURE.getValue());
		result.setMessage(message);
		result.setData(data);
		return result;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	enum Status {
		/**
		 * 成功
		 */
		SUCCESS(1),
		/**
		 * 出错
		 */
		FAILURE(0);

		private int value;

		Status(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}

