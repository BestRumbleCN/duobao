package team.wuxie.crowdfunding.service.impl;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import team.wuxie.crowdfunding.domain.TGoodsType;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.mapper.TGoodsTypeMapper;
import team.wuxie.crowdfunding.service.GoodsTypeService;
import team.wuxie.crowdfunding.service.UserService;
import team.wuxie.crowdfunding.util.service.AbstractService;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<TGoodsType> selectAllLike(Map<String, String> map) {
        return goodsTypeMapper.selectAllLike(map);
    }

    @Override
    public boolean insertOrUpdate(TGoodsType goodsType, Integer operatorId) throws IllegalArgumentException {
        if (goodsType.getTypeId() == null) {
            //add
            Assert.isTrue(goodsTypeMapper.countByTypeName(goodsType.getTypeName()) == 0, "goodsType.typeName_has_existed");
            LOGGER.info(String.format("添加商品分类：typeName=%s，参数=%s", goodsType.getTypeName(), JSON.toJSONString(goodsType)));
            goodsType.setStatus(false);
            goodsType.setCreateTime(new Date());
            goodsType.setCreateId(operatorId);
            return insertSelective(goodsType);
        } else {
            //update
            TGoodsType tem = selectById(goodsType.getTypeId());
            Assert.notNull(tem, "goodsType.not_found");
            Assert.isTrue(goodsTypeMapper.countByTypeName(goodsType.getTypeName()) > 1, "goodsType.typeName_has_existed");
            LOGGER.info(String.format("更新商品分类：typeId=%s，参数=%s", goodsType.getTypeId(), JSON.toJSONString(goodsType)));
            tem = new TGoodsType(
                    tem.getTypeId(),
                    goodsType.getTypeName(),
                    goodsType.getTypeImg(),
                    null,
                    null,
                    null,
                    new Date(),
                    operatorId
            );
            return updateSelective(tem);
        }
    }

    @Override
    public boolean updateStatus(Integer typeId, Integer operatorId) throws IllegalArgumentException {
        TGoodsType goodsType = selectById(typeId);
        Assert.notNull(goodsType, "goodsType.not_found");
        boolean updatedStats = !goodsType.getStatus();
        return goodsTypeMapper.updateStatus(typeId, updatedStats, operatorId) > 0;
    }
}
