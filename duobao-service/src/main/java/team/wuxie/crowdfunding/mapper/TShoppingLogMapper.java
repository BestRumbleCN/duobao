package team.wuxie.crowdfunding.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import team.wuxie.crowdfunding.domain.TShoppingLog;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;
import team.wuxie.crowdfunding.vo.ShoppingLogVO;

public interface TShoppingLogMapper extends BaseMapper<TShoppingLog> {
	
	ShoppingLogVO selectWinnerVOByBidId(@Param("bidId") Integer bidId);

	ShoppingLogVO selectLastWinnerVOByGoodsId(@Param("goodsId") Integer goodsId);
	
	List<ShoppingLogVO> selectWinnerVOsByGoodsId(@Param("goodsId") Integer goodsId);
	
	List<ShoppingLogVO> selectByBidId(@Param("bidId") Integer bidId);
	
	TShoppingLog selectByBidNumAndBIdId(@Param("bidId") Integer bidId,@Param("bidNum")String bidNum);
	
	/**
	 * 查询用户参与某期商品的汇总
	 * @author fly
	 * @return  
	 * @since
	 */
	List<TShoppingLog> selectByUserIdAndBidId(@Param("userId") Integer userId,@Param("bidId") Integer bidId);
}