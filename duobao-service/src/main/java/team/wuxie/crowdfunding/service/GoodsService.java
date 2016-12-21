package team.wuxie.crowdfunding.service;

import org.springframework.transaction.annotation.Transactional;

import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.model.GoodsQuery;
import team.wuxie.crowdfunding.util.service.BaseService;
import team.wuxie.crowdfunding.vo.GoodsBidVO;
import team.wuxie.crowdfunding.vo.GoodsVO;
import team.wuxie.crowdfunding.vo.LuckyShareVo;
import team.wuxie.crowdfunding.vo.ShoppingLogVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品相关Service
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:22
 */
public interface GoodsService extends BaseService<TGoods> {

    /**
     * 查找GoodsVO
     *
     * @return
     */
    List<GoodsVO> selectVOAll(GoodsQuery query);

    /**
     * 模糊查找GoodsVO
     *
     * @param map
     * @return
     */
    List<GoodsVO> selectVOAll(Map<String, Object> map);

    /**
     * 添加或者更新TGoods
     *
     * @param goods
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    boolean insertOrUpdate(TGoods goods) throws IllegalArgumentException;

    /**
     * 更新TGoods状态
     *
     * @param goodsId
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    boolean updateGoodsStatus(Integer goodsId) throws IllegalArgumentException;
    
    /**
     * 根据goodsid查询所有夺宝纪录
     * @param goodsId
     * @return
     */
    List<ShoppingLogVO> selectWinnerLogsByGoodsId(Integer goodsId,Integer pageNum, Integer pageSize);
    
    /**
     * 根据goodsId查询最新一期goodsBidVO
     * @param goodsId
     * @return
     */
    GoodsBidVO selectLastBidByGoodsId(Integer goodsId) throws IllegalArgumentException;
}
