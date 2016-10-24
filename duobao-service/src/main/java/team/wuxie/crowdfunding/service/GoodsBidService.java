package team.wuxie.crowdfunding.service;

import java.util.List;

import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.util.service.BaseService;
import team.wuxie.crowdfunding.vo.GoodsBidVO;

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
	 * 根据类型查询
	 * @author fly
	 * @param queryType	1爆款; 2新货;-1总需;-2进度;0/NULL所有
	 * @return  
	 * @since
	 */
	List<GoodsBidVO> selectByType(Integer queryType,Integer pageNum,Integer pageSize) throws IllegalArgumentException;
}

