package team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 参照<a href="https://pay.weixin.qq.com/wiki/doc/api/app.php?chapter=8_5">官方文档</a> <a
 * href="https://pay.weixin.qq.com/wiki/doc/api/app.php?chapter=9_12&index=2">官方文档2</a><br>
 * 所撰写的客户端所需信息。 因为只有服务器端保存key，所以所有客户端在请求微信调起支付时必须从服务器端获取所需信息。
 * 
 * @author leonliao
 *
 */

public class WechatAppPayRequest extends WeixinPaySignablePdu {
  /**
	 * 
	 */
  private static final long serialVersionUID = -6045775612152015920L;

  // 公众账号ID
  private String appid;
  // 商户号
  private String partnerid;
  // 随机字符串
  private String noncestr;
  // 业务结果, package是java关键字
  private String appPackage;
  // 错误代码
  private String timestamp;
  // 预支付交易会话标识
  private String prepayid;
  
  private String tradeNo;

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getPartnerid() {
    return partnerid;
  }

  public void setPartnerid(String partnerid) {
    this.partnerid = partnerid;
  }

  public String getNoncestr() {
    return noncestr;
  }

  public void setNoncestr(String noncestr) {
    this.noncestr = noncestr;
  }

  public String getAppPackage() {
    return appPackage;
  }

  public void setAppPackage(String appPackage) {
    this.appPackage = appPackage;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getPrepayid() {
    return prepayid;
  }

  public void setPrepayid(String prepayid) {
    this.prepayid = prepayid;
  }

  @Override
  public String toString() {
    return "WechatAppRequest [appid=" + appid + ", partnerid=" + partnerid + ", nonce_str="
        + noncestr + ", appPackage=" + appPackage + ", timestamp=" + timestamp + ", prepay_id="
        + prepayid + ", " + super.toString() + "]";
  }
  
	public Map<String, String> toMapStr(){
		Map<String,String> result = new HashMap<String,String>();
		result.put("appid", this.getAppid());
		result.put("partnerid", this.getPartnerid());
		result.put("prepayid", this.getPrepayid());
		result.put("package", this.getAppPackage());
		result.put("noncestr", this.getNoncestr());
		result.put("timestamp", this.getTimestamp());
		return result;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
}
