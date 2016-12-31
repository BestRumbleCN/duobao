package team.wuxie.crowdfunding.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import team.wuxie.crowdfunding.domain.TShoppingCart;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

public interface TShoppingCartMapper extends BaseMapper<TShoppingCart> {
	TShoppingCart selectByUserIdAndGoodsId(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId);

	List<TShoppingCart> selectByUserId(@Param("userId") Integer userId);

	int deleteByUserIdAndGoodsId(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId);
}