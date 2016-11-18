package team.wuxie.crowdfunding.util.aliyun.dayu;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * <p>
 * 短信服务Service
 * </p>
 *
 * @author wushige
 * @date 2016-08-24 16:56
 */
public final class DayuService {

	private final static Logger LOGGER = LoggerFactory.getLogger(DayuService.class);

	private static TaobaoClient CLIENT = new DefaultTaobaoClient(DayuConfig.BASE_URL, DayuConfig.APP_KEY,
			DayuConfig.APP_SECRET);

	/**
	 * 发送短信响应结果
	 */
	public static class Result {
		public final String code;
		public final String model;
		public final Boolean success;
		public final String msg;

		public Result(String code, String model, Boolean success, String msg) {
			this.code = code;
			this.model = model;
			this.success = success;
			this.msg = msg;
		}
	}

	public interface DayuApi {

		@Headers({ "X-Ca-Key:" + DayuConfig.APP_KEY, "X-Ca-Secret:" + DayuConfig.APP_SECRET })
		@GET(DayuConfig.SEND_SMS)
		Call<Result> sendSms(@Query("rec_num") String cellphone, @Query("sms_param") String smsParam);
	}

	public static void main(String[] args) throws IOException, ApiException {
		// Retrofit retrofit = new Retrofit.Builder()
		// .baseUrl(DayuConfig.BASE_URL)
		// .addConverterFactory(GsonConverterFactory.create())
		// .build();
		//
		// DayuApi dayuApi = retrofit.create(DayuApi.class);
		// Call<Result> call = dayuApi.sendSms("13023179387",
		// String.format("{\"code\": \"%s\"}", "123456"));
		// Result result = call.execute().body();
		// System.out.println(JSON.toJSONString(result));
		String url = "http://gw.api.taobao.com/router/rest";
		String appkey = "23490117";
		String secret = "1db55f7e5b003d193bfc0c4077899f96";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName("注册验证");
		req.setSmsParamString("{\"code\":\"1234\",\"product\":\"信誉_夺宝\"}");
		req.setRecNum("13023179387");
		req.setSmsTemplateCode("SMS_167158461");
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
	}

	/**
	 * 发送注册验证码
	 * 
	 * @author fly
	 * @param cellphone
	 *            手机号
	 * @param code
	 *            验证码
	 * @return
	 * @since
	 */
	public static boolean register(String cellphone, String code) {
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName("注册验证");
		req.setSmsParamString(String.format("{\"code\":\"%s\",\"product\":\"信誉_夺宝\"}", code));
		req.setRecNum(cellphone);
		req.setSmsTemplateCode("SMS_16715846");
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = CLIENT.execute(req);
		} catch (ApiException e) {
			LOGGER.error("短信发送异常！", e);
			return false;
		}
		return checkResult(rsp.getBody(), cellphone);
	}
	
	public static boolean chagenPsw(String cellphone, String code) {
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName("变更验证");
		req.setSmsParamString(String.format("{\"code\":\"%s\",\"product\":\"信誉_夺宝\"}", code));
		req.setRecNum(cellphone);
		req.setSmsTemplateCode("SMS_16715844");
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = CLIENT.execute(req);
		} catch (ApiException e) {
			LOGGER.error("短信发送异常！", e);
			return false;
		}
		return checkResult(rsp.getBody(), cellphone);
	}
	
	public static boolean bindCellphone(String cellphone, String code){
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName("变更验证");
		req.setSmsParamString(String.format("{\"code\":\"%s\",\"product\":\"信誉_夺宝\"}", code));
		req.setRecNum(cellphone);
		req.setSmsTemplateCode("SMS_25730377");
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = CLIENT.execute(req);
		} catch (ApiException e) {
			LOGGER.error("短信发送异常！", e);
			return false;
		}
		return checkResult(rsp.getBody(), cellphone);
	}

	private static boolean checkResult(String result, String cellphone) {
		JSONObject jObject = JSON.parseObject(result);
		if (jObject.containsKey("error_response")) {
			LOGGER.error("短信发送失败,手机号:{},错误码:{}", cellphone,
					jObject.getJSONObject("error_response").getString("sub_code"));
			return false;
		}
		return true;
	}

	// public static void main(String[] args) {
	// System.out.println(String.format("{\"code\": \"%s\"}", "123456"));
	// }
}
