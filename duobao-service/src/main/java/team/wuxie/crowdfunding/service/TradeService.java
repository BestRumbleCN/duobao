package team.wuxie.crowdfunding.service;

import team.wuxie.crowdfunding.domain.TTrade;
import team.wuxie.crowdfunding.domain.enums.TradeSource;
import team.wuxie.crowdfunding.exception.TradeException;
import team.wuxie.crowdfunding.ro.order.OrderRO;
import team.wuxie.crowdfunding.util.service.BaseService;
import team.wuxie.crowdfunding.util.tencent.wechat.wepay.dto.WechatAppPayRequest;

/**
 * ClassName:TradeService <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年11月14日 下午11:55:31
 * @see 	 
 */
public interface TradeService extends BaseService<TTrade> {
	/**
	 * 账户充值
	 * @author fly
	 * @param amount
	 * @param userId
	 * @param tradeSource
	 * @return  支付接口拼接字符串
	 * @since
	 */
	String recharge(float amount, Integer userId, TradeSource tradeSource) throws IllegalArgumentException;
	/**
	 * 微信购买物品
	 * @author fly
	 * @param orderRo
	 * @param userId
	 * @throws IllegalArgumentException  
	 * @since
	 * TODO 添加返回值，细分不同支付
	 */
	WechatAppPayRequest purchase(OrderRO orderRo, Integer userId) throws IllegalArgumentException,TradeException;
}

