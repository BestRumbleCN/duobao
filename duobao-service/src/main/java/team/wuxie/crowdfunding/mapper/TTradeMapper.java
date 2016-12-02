package team.wuxie.crowdfunding.mapper;

import team.wuxie.crowdfunding.domain.TTrade;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

public interface TTradeMapper extends BaseMapper<TTrade> {
	TTrade selectByTradeNo(String tradeNo);
}