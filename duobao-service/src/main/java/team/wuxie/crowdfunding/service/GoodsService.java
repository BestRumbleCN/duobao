package team.wuxie.crowdfunding.service;

import org.springframework.transaction.annotation.Transactional;
import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.util.service.BaseService;
import team.wuxie.crowdfunding.vo.GoodsVO;

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
    List<GoodsVO> selectVOAll();

    /**
     * 模糊查找GoodsVO
     *
     * @param map
     * @return
     */
    List<GoodsVO> selectVOAllLike(Map<String, String> map);

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
}
