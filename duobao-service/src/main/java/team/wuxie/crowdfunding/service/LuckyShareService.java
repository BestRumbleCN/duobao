package team.wuxie.crowdfunding.service;

import java.util.List;

import team.wuxie.crowdfunding.domain.TLuckyShare;
import team.wuxie.crowdfunding.util.service.BaseService;
import team.wuxie.crowdfunding.vo.LuckyShareVo;

public interface LuckyShareService extends BaseService<TLuckyShare> {
	/**
	 * 添加好运分享
	 * @param comment
	 * @param imgs
	 * @param bidId
	 * @param userId
	 * @return
	 * @throws IllegalArgumentException
	 */
	boolean addLuckyShare(String comment, String imgs, Integer bidId, Integer userId) throws IllegalArgumentException;
	/**
	 * 根据用户id查询好运分享纪录（按商品期号desc）
	 * @param userId
	 * @return
	 */
	List<LuckyShareVo> selectByUserId(Integer userId, Integer pageNum, Integer pageSize);
	
	/**
	 * 根据商品Id查询商品好运分享纪录（按商品期号desc）
	 * @param goodsId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<LuckyShareVo> selectByGoodsId(Integer goodsId, Integer pageNum, Integer pageSize);
	
	/**
	 * 查询所有商品好运分享纪录（按商品期号desc）
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<LuckyShareVo> selectAll(Integer pageNum, Integer pageSize);

}
