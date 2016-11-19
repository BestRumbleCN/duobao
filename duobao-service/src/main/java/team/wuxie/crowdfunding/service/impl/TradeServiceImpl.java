package team.wuxie.crowdfunding.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import team.wuxie.crowdfunding.domain.TTrade;
import team.wuxie.crowdfunding.domain.enums.TradeSource;
import team.wuxie.crowdfunding.domain.enums.TradeStatus;
import team.wuxie.crowdfunding.domain.enums.TradeType;
import team.wuxie.crowdfunding.service.TradeService;
import team.wuxie.crowdfunding.util.IdGenerator;
import team.wuxie.crowdfunding.util.aliyun.alipay.AliPayService;
import team.wuxie.crowdfunding.util.service.AbstractService;

/**
 * ClassName:TradeServiceImpl <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年11月15日 上午12:04:21
 * @see
 */
@Service
public class TradeServiceImpl extends AbstractService<TTrade> implements TradeService {

	@Override
	public String recharge(float amount, Integer userId, TradeSource tradeSource) throws IllegalArgumentException {
		Assert.isTrue(amount > 0, "充值金额必须大于零");
		switch (tradeSource) {
		case ALIPAY:
			TTrade trade = new TTrade(null, userId, IdGenerator.generateTradeNo(userId), tradeSource, TradeStatus.WAITTING,
					TradeType.STAMPS, "点劵充值", null, "点劵充值", null, "0.01", null, null);
			this.insertSelective(trade);
			return AliPayService.generatePathParams(trade);
		default:
			break;
		}
		return null;
	}

}
