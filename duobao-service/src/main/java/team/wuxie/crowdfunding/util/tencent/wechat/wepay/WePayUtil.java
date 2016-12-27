package team.wuxie.crowdfunding.util.tencent.wechat.wepay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.ro.order.OrderRO;
import team.wuxie.crowdfunding.ro.order.OrderRO.InnerGoods;
import team.wuxie.crowdfunding.util.HttpUtils;
import team.wuxie.crowdfunding.util.IdGenerator;
import team.wuxie.crowdfunding.util.MD5Utils;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto.UnifiedOrder;

/**
 * ClassName:WePayUtil <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年12月27日 下午5:18:46
 * @see
 */
public class WePayUtil {
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
		//System.out.println(toRequestDetail(innerGoods, bidMap));
		String request = requestXml(or, bidMap, "20161227001");
		System.out.println(HttpUtils.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", request));
		
	}
	
	public static String requestXml(OrderRO orderRo, Map<Integer, TGoodsBid> bidMap, String waybillNo) {
		UnifiedOrder order = new UnifiedOrder();
		order.setAppid(WePayConfig.APP_ID);
		order.setMch_id(WePayConfig.MCH_ID);
		order.setNonce_str(IdGenerator.getRandomString(15));
		order.setBody(WePayConfig.BODY);
		List<InnerGoods> innerGoods = orderRo.getGoodsList();
		order.setDetail(toRequestDetail(innerGoods, bidMap));
		order.setOut_trade_no(waybillNo);
		order.setTotal_fee(orderRo.getTotalCost() * 100);
		order.setSpbill_create_ip(orderRo.getIp());
		Date now = new Date();
		order.setTime_startByDate(now);
		order.setTime_expireByDate(DateUtils.addMinutes(now, 10));
		order.setNotify_url("http://121.196.234.79:8088/login");
		order.setTrade_type(WePayConfig.TRADE_TYPE);
		order.setSign(createSign(order.toMapStr(), "e6d90079055d3734642ea1775fa7cd25"));
		System.out.println(order);
		try {
			return xmlMapper.writeValueAsString(order);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	public static String toXml(UnifiedOrder order){
		return null;
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
	public static String toRequestDetail(List<InnerGoods> innerGoods, Map<Integer, TGoodsBid> bidMap) {
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
		System.out.println("str:"+prestr);
		String md5 = MD5Utils.MD5(prestr);
		System.out.println("md5:"+md5);
		return md5.toUpperCase();
	}
}
