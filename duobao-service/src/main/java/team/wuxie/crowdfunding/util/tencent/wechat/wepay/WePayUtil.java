package team.wuxie.crowdfunding.util.tencent.wechat.wepay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.exception.TradeException;
import team.wuxie.crowdfunding.ro.order.OrderRO;
import team.wuxie.crowdfunding.ro.order.OrderRO.InnerGoods;
import team.wuxie.crowdfunding.util.HttpUtils;
import team.wuxie.crowdfunding.util.IdGenerator;
import team.wuxie.crowdfunding.util.MD5Utils;
import team.wuxie.crowdfunding.util.date.DateUtils;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto.PaymentNotification;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto.UnifiedOrder;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto.UnifiedOrderResponse;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto.WechatAppPayRequest;

/**
 * ClassName:WePayUtil <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年12月27日 下午5:18:46
 * @see
 */
public class WePayUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(WePayUtil.class);

	private static ObjectMapper xmlMapper = new XmlMapper();

	public static void main(String[] args) {
		OrderRO or = new OrderRO();
		or.setIp("192.168.1.1");
		or.setTotalCost(100);
		List<InnerGoods> innerGoods = new ArrayList<InnerGoods>();
		InnerGoods innerGood = new OrderRO.InnerGoods();
		innerGood.setAmount(2);
		innerGood.setBidId(1);
		innerGoods.add(innerGood);
		or.setGoodsList(innerGoods);
		Map<Integer, TGoodsBid> bidMap = new HashMap<Integer, TGoodsBid>();
		TGoodsBid goodsBid = new TGoodsBid();
		goodsBid.setBidId(1);
		goodsBid.setJoinAmount(100);
		goodsBid.setSinglePrice(2);
		bidMap.put(1, goodsBid);
		System.out.println(getAppPayRequest(or, bidMap, "2016122800001"));

	}
	
	/**
	 * 校验微信签名
	 */
	public static boolean validatePayNotiSign(PaymentNotification pNotification){
		String sign = createSign(pNotification.toMapStr(), WePayConfig.APP_KEY);
		return sign.equals(pNotification.getSign());
	}

	public static PaymentNotification getPaymentNotification(String xml) throws TradeException {
		PaymentNotification result = null;
//		xml = xml.replace("<![CDATA[", "");
//		xml = xml.replace("]]>", "");
		try {
			result = xmlMapper.readValue(xml, PaymentNotification.class);
		} catch (Exception e) {
			throw new TradeException("xml转换为对象时失败：", e);
		}
		return result;
	}

	/**
	 * 获取购买商品wx预支付id等支付信息
	 * @param orderRo
	 * @param bidMap
	 * @param waybillNo
	 * @return
	 * @throws TradeException
	 */
	public static WechatAppPayRequest getAppPayRequest(OrderRO orderRo, Map<Integer, TGoodsBid> bidMap,
			String waybillNo) throws TradeException {
		// 1.拼接订单查询参数
		String request = requestXml(orderRo, bidMap, waybillNo);
		String xmlResult = HttpUtils.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", request);
		// 2.获取prepayId
		UnifiedOrderResponse orderResponse = null;
		try {
			orderResponse = xmlMapper.readValue(xmlResult, UnifiedOrderResponse.class);
		} catch (Exception e) {
			throw new TradeException("xml转换为对象时失败：", e);
		}
		if ("FAIL".equals(orderResponse.getReturn_code())) {
			LOGGER.error("订单生成失败：{},订单内容：{}", orderResponse.getReturn_msg(), request);
			throw new TradeException(orderResponse.getReturn_msg());
		}
		if ("FAIL".equals(orderResponse.getResult_code())) {
			LOGGER.error("订单生成失败：{},订单内容：{}", orderResponse.getResult_code(), request);
			throw new TradeException(orderResponse.getErr_code_des());
		}
		// 3.拼接支付请求参数
		WechatAppPayRequest appPayRequest = new WechatAppPayRequest();
		appPayRequest.setAppid(WePayConfig.APP_ID);
		appPayRequest.setPartnerid(WePayConfig.MCH_ID);
		appPayRequest.setPrepayid(orderResponse.getPrepay_id());
		appPayRequest.setNoncestr(IdGenerator.getRandomString(15));
		appPayRequest.setAppPackage(WePayConfig.APP_PACKAGE);
		appPayRequest.setTimestamp(System.currentTimeMillis() / 1000 + "");
		appPayRequest.setSign(createSign(appPayRequest.toMapStr(), WePayConfig.APP_KEY));
		appPayRequest.setTradeNo(waybillNo);
		return appPayRequest;
	}
	
	/**
	 * 获取购买商品wx预支付id等支付信息
	 * @param amount
	 * @param waybillNo
	 * @param ip
	 * @return
	 * @throws TradeException
	 */
	public static WechatAppPayRequest getAppPayRequest(Integer amount, String waybillNo, String ip)
			throws TradeException {
		// 1.拼接订单查询参数
		String request = requestXml(amount, waybillNo, ip);
		String xmlResult = HttpUtils.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", request);
		// 2.获取prepayId
		UnifiedOrderResponse orderResponse = null;
		try {
			orderResponse = xmlMapper.readValue(xmlResult, UnifiedOrderResponse.class);
		} catch (Exception e) {
			throw new TradeException("xml转换为对象时失败：", e);
		}
		if ("FAIL".equals(orderResponse.getReturn_code())) {
			LOGGER.error("订单生成失败：{},订单内容：{}", orderResponse.getReturn_msg(), request);
			throw new TradeException(orderResponse.getReturn_msg());
		}
		if ("FAIL".equals(orderResponse.getResult_code())) {
			LOGGER.error("订单生成失败：{},订单内容：{}", orderResponse.getResult_code(), request);
			throw new TradeException(orderResponse.getErr_code_des());
		}
		// 3.拼接支付请求参数
		WechatAppPayRequest appPayRequest = new WechatAppPayRequest();
		appPayRequest.setAppid(WePayConfig.APP_ID);
		appPayRequest.setPartnerid(WePayConfig.MCH_ID);
		appPayRequest.setPrepayid(orderResponse.getPrepay_id());
		appPayRequest.setNoncestr(IdGenerator.getRandomString(15));
		appPayRequest.setAppPackage(WePayConfig.APP_PACKAGE);
		appPayRequest.setTimestamp(System.currentTimeMillis() / 1000 + "");
		appPayRequest.setSign(createSign(appPayRequest.toMapStr(), WePayConfig.APP_KEY));
		appPayRequest.setTradeNo(waybillNo);
		return appPayRequest;
	}

	/**
	 * 获取购买商品wx请求
	 * @param orderRo
	 * @param bidMap
	 * @param waybillNo
	 * @return
	 * @throws TradeException
	 */
	private static String requestXml(OrderRO orderRo, Map<Integer, TGoodsBid> bidMap, String waybillNo)
			throws TradeException {
		UnifiedOrder order = new UnifiedOrder();
		order.setAppid(WePayConfig.APP_ID);
		order.setMch_id(WePayConfig.MCH_ID);
		order.setNonce_str(IdGenerator.getRandomString(15));
		order.setBody(WePayConfig.BODY);
		List<InnerGoods> innerGoods = orderRo.getGoodsList();
		order.setDetail(toRequestDetail(innerGoods, bidMap));
		order.setOut_trade_no(waybillNo);
		order.setTotal_fee(orderRo.getTotalCost() * 100);
		//order.setTotal_fee(1);
		order.setSpbill_create_ip(orderRo.getIp());
		Date now = new Date();
		order.setTime_startByDate(now);
		order.setTime_expireByDate(DateUtils.addMinutes(now, 10));
		order.setNotify_url(WePayConfig.CALL_BACK_URL);
		order.setTrade_type(WePayConfig.TRADE_TYPE);
		order.setSign(createSign(order.toMapStr(), WePayConfig.APP_KEY));
		try {
			return xmlMapper.writeValueAsString(order);
		} catch (JsonProcessingException e) {
			throw new TradeException("对象转换为xml时失败：", e);
		}
	}
	
	/**
	 * 获取充值抢币wx请求
	 * @param amount
	 * @param waybillNo
	 * @param ip
	 * @return
	 * @throws TradeException
	 */
	private static String requestXml(Integer amount, String waybillNo, String ip)
			throws TradeException {
		UnifiedOrder order = new UnifiedOrder();
		order.setAppid(WePayConfig.APP_ID);
		order.setMch_id(WePayConfig.MCH_ID);
		order.setNonce_str(IdGenerator.getRandomString(15));
		order.setBody(WePayConfig.BODY);
		order.setDetail(toRequestDetail(amount));
		order.setOut_trade_no(waybillNo);
		order.setTotal_fee(amount * 100);
		//order.setTotal_fee(1);
		order.setSpbill_create_ip(ip);
		Date now = new Date();
		order.setTime_startByDate(now);
		order.setTime_expireByDate(DateUtils.addMinutes(now, 10));
		order.setNotify_url(WePayConfig.CALL_BACK_URL);
		order.setTrade_type(WePayConfig.TRADE_TYPE);
		order.setSign(createSign(order.toMapStr(), WePayConfig.APP_KEY));
		try {
			return xmlMapper.writeValueAsString(order);
		} catch (JsonProcessingException e) {
			throw new TradeException("对象转换为xml时失败：", e);
		}
	}

	/**
	 * 订单信息转换为wx商品详情
	 * 
	 * @author fly
	 * @param innerGoods
	 * @param bidMap
	 * @return
	 * @since
	 */
	private static String toRequestDetail(List<InnerGoods> innerGoods, Map<Integer, TGoodsBid> bidMap) {
		JSONObject root = new JSONObject();
		JSONArray jArray = new JSONArray();
		for (InnerGoods innerGood : innerGoods) {
			JSONObject jo = new JSONObject();
			/**
			 * "goods_id":"iphone6s_32G", "wxpay_goods_id":"1002", "goods_name":
			 * "iPhone6s 32G", "quantity":1, "price":608800,
			 * "goods_category":"123789", "body":"苹果手机"
			 */
			TGoodsBid bid = bidMap.get(innerGood.getBidId());
			jo.put("goods_id", innerGood.getBidId() + "");
			// jo.put("wxpay_goods_id", "1001");
			jo.put("goods_name", "商品" + innerGood.getBidId());
			jo.put("quantity", innerGood.getAmount());
			jo.put("price", bid.getSinglePrice() * 100);
			// jo.put("goods_category", "123789");
			// jo.put("body", bid.get);
			jArray.add(jo);
		}
		root.put("goods_detail", jArray);
		return root.toJSONString();
	}
	
	/**
	 * 充值信息转换为wx商品详情
	 * @param amount
	 * @return
	 */
	private static String toRequestDetail(Integer amount){
		JSONObject root = new JSONObject();
		JSONArray jArray = new JSONArray();
		JSONObject jo = new JSONObject();
		/**
		 * "goods_id":"iphone6s_32G", "wxpay_goods_id":"1002", "goods_name":
		 * "iPhone6s 32G", "quantity":1, "price":608800,
		 * "goods_category":"123789", "body":"苹果手机"
		 */
		jo.put("goods_id", amount + "抢币");
		// jo.put("wxpay_goods_id", "1001");
		jo.put("goods_name", "抢币");
		jo.put("quantity", 1);
		jo.put("price", amount * 100);
		// jo.put("goods_category", "123789");
		// jo.put("body", bid.get);
		jArray.add(jo);
		root.put("goods_detail", jArray);
		return root.toJSONString();
	}

	/**
	 * 生成签名
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createSign(Map<String, String> params, String apiKey) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			prestr = prestr + key + "=" + value + "&";
		}
		prestr = prestr + "key=" + apiKey;
		// System.out.println("str:"+prestr);
		String md5 = MD5Utils.MD5(prestr);
		// System.out.println("md5:"+md5);
		return md5.toUpperCase();
	}
}
