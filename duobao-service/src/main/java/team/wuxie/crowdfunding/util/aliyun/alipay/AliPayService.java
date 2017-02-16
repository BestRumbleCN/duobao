package team.wuxie.crowdfunding.util.aliyun.alipay;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @author   fly
 * @version  1.0
 * @since    2016年11月13日 下午9:12:05
 * @see 	 
 */
public class AliPayService {
	private final static Logger LOGGER = LoggerFactory.getLogger(AliPayService.class);
	
	public static boolean signVerified(Map<String,String> paramsMap){
		try {
			return AlipaySignature.rsaCheckV1(paramsMap, AlipayConfig.PUBLIC_KEY, AlipayConfig.CHARSET);
		} catch (AlipayApiException e) {
			LOGGER.error("校验签名失败", e);
			return false;
		}
	}
	public static String generatePathParams(TTrade trade){
		Map<String,String> params = new HashMap<String,String>();
		params.put("app_id", AlipayConfig.APPID);
		params.put("method", AlipayConfig.METHOD);
		params.put("format", AlipayConfig.FORMAT);
		params.put("charset", AlipayConfig.CHARSET);
		params.put("sign_type", AlipayConfig.SIGN_TYPE);
		params.put("timestamp", DateFormatUtils.format(DateFormatUtils.DEFAULT_DATETIME_PATTERN2));
		params.put("version", AlipayConfig.VERSION);
		params.put("notify_url", AlipayConfig.NOTIFY_URL);
		String biz_content = generateBizContent(trade);
		System.out.println(biz_content);
		params.put("biz_content", biz_content);
		try {
			params.put("sign", AlipaySignature.rsaSign(params, AlipayConfig.PRIMARY_KEY, AlipayConfig.CHARSET));
		} catch (AlipayApiException e) {
			LOGGER.error("", e.getErrMsg());
		}
		try {
			return mapToString(params);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 生成biz_content
	 * @author fly
	 * @return  
	 * @since
	 */
	private static String generateBizContent(TTrade trade){
		Map<String,String> bizContent = new HashMap<String,String>();
		bizContent.put("body", trade.getDescription());
		bizContent.put("subject", trade.getKeyword());
		bizContent.put("out_trade_no", trade.getTradeNo());
		bizContent.put("total_amount", trade.getAmount());
		//bizContent.put("seller_id", "");
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
	
	private static String mapToString(Map<String,String> params) throws UnsupportedEncodingException{
		StringBuilder result = new StringBuilder();
		for(String key : params.keySet()){
			result.append(key).append("=\"");
			result.append(params.get(key)).append("\"&");
		}
		System.out.println(JSON.toJSONString(params));
		return result.substring(0, result.length() - 1);
	}
	public static void main(String[] args) throws AlipayApiException, UnsupportedEncodingException {
//		TTrade trade = new TTrade();
//		trade.setAmount("0.01");
//		trade.setDescription("信誉夺宝");
//		trade.setKeyword("充值");
//		trade.setTradeNo("123456789");
//		System.out.println(generatePathParams(trade));
		Map<String,String> params = new HashMap<String,String>();
		params.put("service", "mobile.securitypay.pay");
		params.put("partner", "2088421781289181");
		params.put("_input_charset", "utf-8");
		params.put("notify_url", "http://121.196.234.79:8088/finance/callback");
		params.put("out_trade_no", "0819145412-6177");
		params.put("subject", "测完");
		params.put("payment_type", "1");
		params.put("seller_id", "9270616@qq.com");
		params.put("body", "测试数据");
		params.put("total_fee", "0.01");
		String signValue = getSignContent(params);
		//params.put("it_b_pay", "30m");
		//params.put("it_b_pay", "30m");
//		params.put("sign", );
//		params.put("sign_type", "RSA");
		System.out.println(signValue);
		signValue = signValue + "&sign=\"" + AlipaySignature.rsaSign(signValue, AlipayConfig.PRIMARY_KEY, AlipayConfig.CHARSET) +"\"&sign_type=\"RSA\"";
		System.out.println(signValue);
		
	}
}

