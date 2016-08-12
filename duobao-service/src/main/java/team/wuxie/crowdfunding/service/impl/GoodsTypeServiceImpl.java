package team.wuxie.crowdfunding.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.wuxie.crowdfunding.domain.TGoodsType;
import team.wuxie.crowdfunding.mapper.TGoodsTypeMapper;
import team.wuxie.crowdfunding.service.GoodsTypeService;
import team.wuxie.crowdfunding.util.service.AbstractService;

/**
 * <p>
 * 商品类型相关ServiceImpl
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:25
 */
@Service
public class GoodsTypeServiceImpl extends AbstractService<TGoodsType> implements GoodsTypeService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    TGoodsTypeMapper goodsTypeMapper;
}
