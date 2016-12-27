package team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import team.wuxie.crowdfunding.util.date.DateFormatUtils;

/**
 * 
 * 参照<a
 * href="https://pay.weixin.qq.com/wiki/doc/api/app.php?chapter=9_1">官方文档</a> <br>
 * 所撰写的统一下单实体类
 * 
 * @author leonliao
 *
 */
@JacksonXmlRootElement(localName = "xml")
@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect
public class UnifiedOrder extends WeixinPaySignablePdu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4990302734672853683L;

	static final String TRADE_TYPE = "APP";

	public UnifiedOrder() {
		this.trade_type = TRADE_TYPE;
	}

	// 公众账号ID
	private String appid;
	// 商户号
	private String mch_id;
	// 设备号
	//private String device_info;
	// 随机字符串
	private String nonce_str;
	// 商品描述
	private String body;
	// 商品详情
	private String detail;
	// 附加数据
	//private String attach;
	// 商户订单号
	private String out_trade_no;
	// 货币类型
	//private String fee_type;
	// 总金额
	private Integer total_fee;

	// 终端IP
	private String spbill_create_ip;
	// 交易起始时间
	private String time_start;
	// 交易结束时间订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则.注意：最短失效时间间隔必须大于5分钟;
	private String time_expire;
	// 商品标记
	//private String goods_tag;
	// 通知地址
	private String notify_url;
	// 交易类型
	private String trade_type;
	// 商品ID
	private String product_id;
	// 指定支付方式
	//private String limit_pay;
	// 用户标识
	//private String openid;

	public String getAppid() {
		return appid;
	}
	@XmlElement
	public UnifiedOrder setAppid(String appid) {
		this.appid = appid;
		return this;
	}

	public String getMch_id() {
		return mch_id;
	}
	@XmlElement
	public UnifiedOrder setMch_id(String mch_id) {
		this.mch_id = mch_id;
		return this;
	}

//	public String getDevice_info() {
//		return device_info;
//	}
//
//	public UnifiedOrder setDevice_info(String device_info) {
//		this.device_info = device_info;
//		return this;
//	}

	public String getNonce_str() {
		return nonce_str;
	}
	@XmlElement
	public UnifiedOrder setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
		return this;
	}

	public String getBody() {
		return body;
	}
	@XmlElement
	public UnifiedOrder setBody(String body) {
		this.body = body;
		return this;
	}

	public String getDetail() {
		return detail;
	}
	@XmlElement
	public UnifiedOrder setDetail(String detail) {
		this.detail = detail;
		return this;
	}
//
//	public String getAttach() {
//		return attach;
//	}
//
//	public UnifiedOrder setAttach(String attach) {
//		this.attach = attach;
//		return this;
//	}

	public String getOut_trade_no() {
		return out_trade_no;
	}
	@XmlElement
	public UnifiedOrder setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
		return this;
	}

//	public String getFee_type() {
//		return fee_type;
//	}
//
//	public UnifiedOrder setFee_type(String fee_type) {
//		this.fee_type = fee_type;
//		return this;
//	}

	public Integer getTotal_fee() {
		return total_fee;
	}
	@XmlElement
	public UnifiedOrder setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
		return this;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public UnifiedOrder setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
		return this;
	}

	public String getTime_start() {
		return time_start;
	}
	public UnifiedOrder setTime_startByDate(Date time_start) {
		this.time_start = DateFormatUtils.wxTimeFormat(time_start);
		return this;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	@JsonIgnore
	public Date getTime_startAsDate() {
		return DateFormatUtils.formatStringToDate(time_start,DateFormatUtils.WX_DATE_FORMAT);
	}
	@JsonIgnore
	public Date getTime_expireAsDate() {
		return DateFormatUtils.formatStringToDate(time_expire,DateFormatUtils.WX_DATE_FORMAT);
	}
	public String getTime_expire() {
		return time_expire;
	}

	public UnifiedOrder setTime_expireByDate(Date time_expire) {
		this.time_expire = DateFormatUtils.wxTimeFormat(time_expire);
		return this;
	}

//	public String getGoods_tag() {
//		return goods_tag;
//	}
//
//	public UnifiedOrder setGoods_tag(String goods_tag) {
//		this.goods_tag = goods_tag;
//		return this;
//	}

	public String getNotify_url() {
		return notify_url;
	}
	@XmlElement
	public UnifiedOrder setNotify_url(String notify_url) {
		this.notify_url = notify_url;
		return this;
	}

	public String getTrade_type() {
		return trade_type;
	}
	public UnifiedOrder setTrade_type(String trade_type) {
		this.trade_type = trade_type;
		return this;
	}

	public String getProduct_id() {
		return product_id;
	}
	
	public UnifiedOrder setProduct_id(String product_id) {
		this.product_id = product_id;
		return this;
	}

//	public String getLimit_pay() {
//		return limit_pay;
//	}
//
//	public UnifiedOrder setLimit_pay(String limit_pay) {
//		this.limit_pay = limit_pay;
//		return this;
//	}
//
//	public String getOpenid() {
//		return openid;
//	}
//
//	public UnifiedOrder setOpenid(String openid) {
//		this.openid = openid;
//		return this;
//	}

	public Map<String,String> toMapStr(){
		Map<String,String> result = new HashMap<String,String>();
		result.put("appid", this.getAppid());
		result.put("mch_id", this.getMch_id());
		result.put("nonce_str", this.getNonce_str());
		result.put("body", this.getBody());
		result.put("detail", this.getDetail());
		result.put("out_trade_no", this.getOut_trade_no());
		result.put("total_fee", this.getTotal_fee()+"");
		result.put("spbill_create_ip", this.getSpbill_create_ip());
		result.put("time_start", this.getTime_start());
		result.put("time_expire", this.getTime_expire());
		result.put("notify_url", this.getNotify_url());
		result.put("trade_type", this.getTrade_type());
		return result;
	}
}
