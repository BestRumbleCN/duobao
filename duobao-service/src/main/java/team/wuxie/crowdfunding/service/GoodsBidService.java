package team.wuxie.crowdfunding.service;

import java.util.List;

import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.util.service.BaseService;
import team.wuxie.crowdfunding.vo.GoodsBidDetailVO;
import team.wuxie.crowdfunding.vo.GoodsBidVO;
import team.wuxie.crowdfunding.vo.ShoppingLogVO;
import team.wuxie.crowdfunding.vo.UserGoodsBidDetailVO;

/**
 * ClassName:GoodsBidService <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年10月24日 下午4:40:55
 * @see 	 
 */
public interface GoodsBidService extends BaseService<TGoodsBid> {
	
	/**
	 * 根据goods生成goodsBid
	 * @author fly
	 * @param goods
	 * @return  
	 * @since
	 */
	boolean generateAndAdd(TGoods goods) throws IllegalArgumentException;
	
	/**
	 * 根据频道查询
	 * @author fly
	 * @param queryType	1爆款; 2新货;-1总需;-2进度;0/NULL所有
	 * @return  
	 * @since
	 */
	List<GoodsBidVO> selectByChannel(Integer queryType,Integer pageNum,Integer pageSize) throws IllegalArgumentException;

	/**
	 * 根据分类id统计
	 * @author fly
	 * @param typeId	1 一元 2十元
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws IllegalArgumentException  
	 * @since
	 */
	List<GoodsBidVO> selectByTypeId(Integer typeId,Integer pageNum,Integer pageSize) throws IllegalArgumentException;
	
	/**
	 * 
	 * @author fly
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @return  
	 * @since
	 */
	public List<GoodsBidVO> selectVOsByName(String name, Integer pageNum, Integer pageSize);
	
	/**
	 * 随机查询
	 * @author fly
	 * @return  
	 * @since
	 */
	List<GoodsBidVO> selectVoRandom();
	
	
	/**
	 * 查询待揭晓商品
	 * @author fly
	 * @return  
	 * @since
	 */
	List<GoodsBidVO> selectTobePublished(Integer pageNum,Integer pageSize);
	
	/**
	 * 根据期号查询商品详情
	 * @author fly
	 * @return  
	 * @since
	 */
	GoodsBidDetailVO selectDetailByBidId(Integer bidId);
	
	/**
	 * 按状态条件查询单个用户参与记录
	 * @author fly
	 * @param userId
	 * @param status 1进行中 4已揭晓
	 * @return  
	 * @since
	 */
	List<UserGoodsBidDetailVO> selectByUserIdAndStatus(Integer userId, Integer status);
	
	/**
	 * 查询用户中奖记录
	 * @author fly
	 * @param userId
	 * @return  
	 * @since
	 */
	List<UserGoodsBidDetailVO> selectLuckyByUserId(Integer userId);
	
	/**
	 * 查询单个用户所有参与记录
	 * @author fly
	 * @param userId
	 * @return  
	 * @since
	 */
	List<GoodsBidVO> selectByUserId(Integer userId);
	
	
	/**
	 * 根据期号查询夺宝购买记录
	 * @author fly
	 * @param bidId
	 * @param pageNum
	 * @param pageSize
	 * @return  
	 * @since
	 */
	List<ShoppingLogVO> selectShoppingLogByBidId(Integer bidId, Integer pageNum, Integer pageSize);
}

