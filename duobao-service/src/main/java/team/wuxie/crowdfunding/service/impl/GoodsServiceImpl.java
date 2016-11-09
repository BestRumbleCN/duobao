package team.wuxie.crowdfunding.service.impl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.mapper.TGoodsMapper;
import team.wuxie.crowdfunding.service.GoodsBidService;
import team.wuxie.crowdfunding.service.GoodsService;
import team.wuxie.crowdfunding.util.service.AbstractService;
import team.wuxie.crowdfunding.vo.GoodsVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品相关ServiceImpl
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:23
 */
@Service
public class GoodsServiceImpl extends AbstractService<TGoods> implements GoodsService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    TGoodsMapper goodsMapper;
    
    @Autowired
    GoodsBidService goodsBidService;

    @Override
    public List<GoodsVO> selectVOAll() {
        return goodsMapper.selectVOAll();
    }

    @Override
    public List<GoodsVO> selectVOAll(Map<String, Object> map) {
        return goodsMapper.selectVOs(map);
    }

    @Override
    public boolean insertOrUpdate(TGoods goods) throws IllegalArgumentException {
        if (goods.getGoodsId() == null) {
            //add
            Assert.isTrue(goodsMapper.countByGoodsName(goods.getGoodsName()) == 0, "goods.goodsName_has_existed");
            LOGGER.info(String.format("添加商品：goodsName=%s，参数=%s", goods.getGoodsName(), JSON.toJSONString(goods)));
            goods.setGoodsStatus(false);
            return insertSelective(goods);
        } else {
            //update
            TGoods tem = selectById(goods.getGoodsId());
            Assert.notNull(tem, "goods.not_found");
            Assert.isTrue(goodsMapper.countByGoodsName(goods.getGoodsName()) <= 1, "goods.goodsName_has_existed");
            LOGGER.info(String.format("更新商品：goodsId=%s，参数=%s", goods.getGoodsId(), JSON.toJSONString(goods)));
            tem = new TGoods(
                    tem.getGoodsId(),
                    goods.getTypeId(),
                    goods.getChannel(),
                    goods.getGoodsName(),
                    null,
                    goods.getStatement(),
                    goods.getTotalAmount(),
                    goods.getSinglePrice(),
                    goods.getImg(),
                    goods.getImgDetail(),
                    null,
                    new Date()
            );
            return updateSelective(tem);
        }
    }

    @Override
    public boolean updateGoodsStatus(Integer goodsId) throws IllegalArgumentException {
        TGoods goods = selectById(goodsId);
        Assert.notNull(goods, "goods.not_found");
        boolean updatedGoodsStatus = !goods.getGoodsStatus();
        if(updatedGoodsStatus){
        	goodsBidService.generateAndAdd(goods);
        }
        return goodsMapper.updateGoodsStatus(goodsId, updatedGoodsStatus) > 0;
    }
}
