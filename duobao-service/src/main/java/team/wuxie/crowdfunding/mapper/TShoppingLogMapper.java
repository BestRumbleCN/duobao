package team.wuxie.crowdfunding.mapper;

import org.apache.ibatis.annotations.Param;

import team.wuxie.crowdfunding.domain.TShoppingLog;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;
import team.wuxie.crowdfunding.vo.ShoppingLogVO;

public interface TShoppingLogMapper extends BaseMapper<TShoppingLog> {
	
	ShoppingLogVO selectWinnerVOByBidId(@Param("bidId") Integer bidId);

	ShoppingLogVO selectLastWinnerVOByGoodsId(@Param("goodsId") Integer goodsId);
}