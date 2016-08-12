package team.wuxie.crowdfunding.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.mapper.TGoodsMapper;
import team.wuxie.crowdfunding.service.GoodsService;
import team.wuxie.crowdfunding.util.service.AbstractService;

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
}
