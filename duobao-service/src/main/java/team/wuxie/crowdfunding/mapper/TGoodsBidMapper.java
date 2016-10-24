package team.wuxie.crowdfunding.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;
import team.wuxie.crowdfunding.vo.GoodsBidVO;

public interface TGoodsBidMapper extends BaseMapper<TGoodsBid> {
	
	TGoodsBid selectLastByGoodsId(@Param("goodsId") Integer goodsId);
	
	List<GoodsBidVO> selectVOsByChannel(@Param("channel") Integer channel);
	
	List<GoodsBidVO> selectVOsByTotalAmount();

	List<GoodsBidVO> selectVOsByPercent();
	
	List<GoodsBidVO> selectAllVOs();
}