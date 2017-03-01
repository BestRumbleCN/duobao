package team.wuxie.crowdfunding.util.aliyun.alipay;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.util.URLEncoder;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.StringUtils;

import team.wuxie.crowdfunding.domain.TGoodsBid;
//
//import com.alibaba.fastjson.JSON;
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.internal.util.AlipaySignature;
//import com.alipay.api.internal.util.StringUtils;
//
import team.wuxie.crowdfunding.domain.TTrade;
import team.wuxie.crowdfunding.ro.order.OrderRO;
//import team.wuxie.crowdfunding.util.date.DateFormatUtils;
import team.wuxie.crowdfunding.util.date.DateFormatUtils;

/**
 * ClassName:AliPayService <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月13日 下午9:12:05
 * @see
 */
public class AliPayService {
	private final static Logger LOGGER = LoggerFactory.getLogger(AliPayService.class);

	public static boolean signVerified(Map<String, String> paramsMap) {
		try {
			String sign = paramsMap.get("sign");
			return AlipaySignature.rsa256CheckContent(AlipaySignature.getSignCheckContentV1(paramsMap),sign, AlipayConfig.PUBLIC_KEY, AlipayConfig.CHARSET);
		} catch (AlipayApiException e) {
			LOGGER.error("校验签名失败", e);
			return false;
		}
	}
	public static String generatePathParams(TTrade trade) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("app_id", AlipayConfig.APPID);
		params.put("method", AlipayConfig.METHOD);
		// params.put("format", AlipayConfig.FORMAT);
		params.put("charset", AlipayConfig.CHARSET);
		params.put("sign_type", AlipayConfig.SIGN_TYPE);
		params.put("timestamp", DateFormatUtils.format(DateFormatUtils.DEFAULT_DATETIME_PATTERN2));
		params.put("version", AlipayConfig.VERSION);
		params.put("notify_url", AlipayConfig.NOTIFY_URL);
		String biz_content = generateBizContent(trade);
		params.put("biz_content", biz_content);
		try {
			params.put("sign", AlipaySignature.rsa256Sign(AlipaySignature.getSignContent(params),
					AlipayConfig.PRIMARY_KEY, AlipayConfig.CHARSET));
		} catch (AlipayApiException e) {
			LOGGER.error("", e.getErrMsg());
			return null;
		}
		return mapToString(params);
	}

	/**
	 * 生成biz_content
	 * 
	 * @author fly
	 * @return
	 * @since
	 */
	private static String generateBizContent(TTrade trade) {
		Map<String, String> bizContent = new HashMap<String, String>();
		bizContent.put("body", trade.getDescription());
		bizContent.put("subject", trade.getKeyword());
		bizContent.put("out_trade_no", trade.getTradeNo());
		// bizContent.put("total_amount", trade.getAmount());
		bizContent.put("total_amount", "0.01");
		// bizContent.put("seller_id", "");
		bizContent.put("product_code", "QUICK_MSECURITY_PAY");
		bizContent.put("goods_type", "1");
		return JSON.toJSONString(bizContent);
	}

	private static String getSignContent(Map<String, String> sortedParams) {
		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(sortedParams.keySet());
		Collections.sort(keys);
		int index = 0;
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = sortedParams.get(key);
			if (StringUtils.areNotEmpty(key, value)) {
				content.append((index == 0 ? "" : "&") + key + "=\"" + value + "\"");
				index++;
			}
		}
		return content.toString();
	}

	private static String mapToString(Map<String, String> params) {
		StringBuilder result = new StringBuilder();
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		for (String key : keys) {
			if (key.equals("sign")) {
				continue;
			}
			result.append(key).append("=");
			result.append(URLEncoder.DEFAULT.encode(params.get(key))).append("&");
		}
		result.append("sign=").append(URLEncoder.DEFAULT.encode(params.get("sign")));
		return result.toString();
	}

	public static void main(String[] args) throws AlipayApiException, UnsupportedEncodingException {
		//System.out.println(AlipaySignature.rsa256CheckContent("app_id=2017010904940527&auth_app_id=2017010904940527&body=众筹夺宝&buyer_id=2088112222853268&buyer_logon_id=181****3736&buyer_pay_amount=0.01&charset=utf-8&fund_bill_list=[{\"amount\":\"0.01\",\"fundChannel\":\"ALIPAYACCOUNT\"}]&gmt_create=2017-03-01 09:59:43&gmt_payment=2017-03-01 09:59:43&invoice_amount=0.01&notify_id=162e0c1f36ece74f7b37df11b17b2efi0a&notify_time=2017-03-01 10:06:40&notify_type=trade_status_sync&out_trade_no=20170301100014&point_amount=0.00&receipt_amount=0.01&seller_email=9270616@qq.com&seller_id=2088421781289181&subject=众筹夺宝&total_amount=0.01&trade_no=2017030121001004260207201045&trade_status=TRADE_SUCCESS&version=1.0", "mecOp5O9rDOt+2bsshY9JtJpKSqugn1jjYf1pr7eYd24j0Zwb9PW05bo9JEpAN9vnGzVzcTPUSgC1n16PRtStGF7nPfY03PW+Tcvnvcr5hJCJPVtNRa6a511w/Yi7WPPoE9ikOmf5XQmUN4xU/Jx1Lu46ztU24quVdcNn24S3ckN8HwszLNEJghlC6DH9zaKEUQCm27pTscRh42NitP/W1V0rn0rzm8bSEVJCL63BS6DYFI6MqAqCXgVk+Vt+cD6FZvYPREk9fXveOpNaUnW3p3GzPfynQIu8UZCrJoTrP7s7qDTMGIWYuUtHn36znzkSwkm7ePp0hq3xDdlkUvC8A==", AlipayConfig.PUBLIC_KEY, AlipayConfig.CHARSET));
	}
}
