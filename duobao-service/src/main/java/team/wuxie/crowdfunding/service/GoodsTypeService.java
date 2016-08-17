package team.wuxie.crowdfunding.service;

import org.springframework.transaction.annotation.Transactional;
import team.wuxie.crowdfunding.domain.TGoodsType;
import team.wuxie.crowdfunding.util.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品类型相关Service
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:25
 */
public interface GoodsTypeService extends BaseService<TGoodsType> {

    /**
     * 模糊查询商品分类
     *
     * @param map
     * @return
     */
    List<TGoodsType> selectAllLike(Map<String, String> map);

    /**
     * 添加或者更新商品分类
     *
     * @param goodsType
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    boolean insertOrUpdate(TGoodsType goodsType) throws IllegalArgumentException;

    @Transactional
    boolean updateStatus(Integer typeId) throws IllegalArgumentException;
}
