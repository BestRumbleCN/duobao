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

//
//import com.alibaba.fastjson.JSON;
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.internal.util.AlipaySignature;
//import com.alipay.api.internal.util.StringUtils;
//
import team.wuxie.crowdfunding.domain.TTrade;
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
			return AlipaySignature.rsaCheckV1(paramsMap, AlipayConfig.PUBLIC_KEY, AlipayConfig.CHARSET);
		} catch (AlipayApiException e) {
			LOGGER.error("校验签名失败", e);
			return false;
		}
	}

	public static String generateOldPathParams(TTrade trade) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("service", "mobile.securitypay.pay");
		params.put("partner", "2088421781289181");
		params.put("_input_charset", AlipayConfig.CHARSET);
		// params.put("sign_type", AlipayConfig.SIGN_TYPE);
		params.put("notify_url", AlipayConfig.NOTIFY_URL);
		params.put("out_trade_no", trade.getTradeNo());
		params.put("subject", trade.getKeyword());
		params.put("body", trade.getKeyword());
		params.put("total_fee", trade.getAmount());
		params.put("total_fee", "0.01");
		params.put("payment_type", "1");
		// bizContent.put("seller_id", "");
		params.put("seller_id", "9270616@qq.com");
		String result = mapToString(params);
		try {
			params.put("sign", AlipaySignature.rsaSign(result, AlipayConfig.PRIMARY_KEY, AlipayConfig.CHARSET));
		} catch (AlipayApiException e) {
			LOGGER.error("", e.getErrMsg());
			return null;
		}
		result = result + "&sign=\"" + params.get("sign") + "\"&sign_type=\"" + AlipayConfig.SIGN_TYPE + "\"";
		return result;
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

	}
}
