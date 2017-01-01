package team.wuxie.crowdfunding.mapper;

import java.util.List;

import team.wuxie.crowdfunding.domain.TTrade;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

public interface TTradeMapper extends BaseMapper<TTrade> {
	TTrade selectByTradeNo(String tradeNo);
	List<TTrade> seleceOverTimeTrade();
}