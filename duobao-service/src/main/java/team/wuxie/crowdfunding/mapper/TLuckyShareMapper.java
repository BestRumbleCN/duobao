package team.wuxie.crowdfunding.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import team.wuxie.crowdfunding.domain.TLuckyShare;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

public interface TLuckyShareMapper extends BaseMapper<TLuckyShare> {
	List<TLuckyShare> selectByUserId(@Param("userId") Integer userId);

	List<TLuckyShare> selectByGoodsId(@Param("goodsId") Integer goodsId);

	List<TLuckyShare> selectAll();

}