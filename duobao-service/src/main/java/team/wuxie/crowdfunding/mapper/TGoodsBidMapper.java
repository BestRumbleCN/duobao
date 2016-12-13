package team.wuxie.crowdfunding.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;
import team.wuxie.crowdfunding.vo.GoodsBidVO;
import team.wuxie.crowdfunding.vo.UserGoodsBidDetailVO;

public interface TGoodsBidMapper extends BaseMapper<TGoodsBid> {

	GoodsBidVO selectLastByGoodsId(@Param("goodsId") Integer goodsId);

	List<GoodsBidVO> selectVOsByChannel(@Param("channel") Integer channel);

	List<GoodsBidVO> selectVOsByType(@Param("typeId") Integer typeId);

	List<GoodsBidVO> selectVOsByName(@Param("name") String name);

	List<GoodsBidVO> selectVOsByTotalAmount();

	List<GoodsBidVO> selectVOsByPercent();

	List<GoodsBidVO> selectAllVOs();

	List<GoodsBidVO> selectTobePublished();

	List<UserGoodsBidDetailVO> selectByUserIdAndStatus(@Param("userId") Integer userId,
			@Param("bidStatus") Integer bidStatus);

	List<UserGoodsBidDetailVO> selectLuckyByUserId(@Param("userId") Integer userId);

	GoodsBidVO selectVoByBidId(@Param("bidId") Integer bidId);

	List<GoodsBidVO> selectVoRandom();

	/**
	 * 
	 * @author fly
	 * @param joinAmount
	 * @param bidId
	 * @since
	 */
	void addJoinAmount(@Param("joinAmount") Integer joinAmount, @Param("bidId") Integer bidId);
}