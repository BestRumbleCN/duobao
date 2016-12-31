package team.wuxie.crowdfunding.domain.support;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.domain.TGoodsBid;
import team.wuxie.crowdfunding.domain.TGoodsType;
import team.wuxie.crowdfunding.domain.enums.BidStatus;
import team.wuxie.crowdfunding.mapper.TGoodsBidMapper;
import team.wuxie.crowdfunding.mapper.TGoodsMapper;
import team.wuxie.crowdfunding.mapper.TGoodsTypeMapper;
import team.wuxie.crowdfunding.model.GoodsBidQuery;
import team.wuxie.crowdfunding.model.GoodsQuery;
import team.wuxie.crowdfunding.util.page.DtModel;
import team.wuxie.crowdfunding.util.page.Page;
import team.wuxie.crowdfunding.vo.GoodsBidVO;
import team.wuxie.crowdfunding.vo.GoodsVO;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.google.common.base.Preconditions.*;
import static org.springframework.util.StringUtils.hasText;

/**
 * Goods: 商品相关领域模型 -- 涉及商品表、商品分类表、商品竞购表
 *
 * @author WuGang
 * @since 1.0
 */
@SuppressWarnings("unused")
public class Goods implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private TGoodsMapper goodsMapper;
    private TGoodsTypeMapper goodsTypeMapper;
    private TGoodsBidMapper goodsBidMapper;

    private Goods() {
    }

    private static final Goods INSTANCE = new Goods();

    public static Goods getInstance() {
        return INSTANCE;
    }

    public static TGoodsMapper goodsMapper() {
        return INSTANCE.goodsMapper;
    }

    public static TGoodsTypeMapper goodsTypeMapper() {
        return INSTANCE.goodsTypeMapper;
    }

    public static TGoodsBidMapper goodsBidMapper() {
        return INSTANCE.goodsBidMapper;
    }

    @Nullable
    @Contract("null -> null")
    public static TGoods selectById(Integer goodsId) {
        return goodsMapper().selectByPrimaryKey(goodsId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TGoods selectByIdOrFail(Integer goodsId) {
        checkArgument(goodsId != null && goodsId > 0, "goodsId illegal");
        TGoods goods = selectById(goodsId);
        goodsNotFound(goods);
        return goods;
    }

    public static boolean existsByGoodsName(String goodsName, @Nullable Integer goodsId) {
        if (goodsId == null) {  //添加
            return hasText(goodsName) && goodsMapper().countByGoodsName(goodsName) > 0;
        } else {  //编辑
            TGoods goods = selectByIdOrFail(goodsId);
            return  hasText(goodsName) && goodsMapper().countByGoodsName(goodsName) >= 1
                    && !goods.getGoodsName().equals(goodsName);
        }
    }

    public static Page<GoodsVO> findGoodsVOPage(String table, GoodsQuery query) {
        DtModel dtModel = JSON.parseObject(table, DtModel.class);
        PageHelper.startPage(dtModel.getPageNum(), dtModel.getLength(), dtModel.getOrderBy());
        List<GoodsVO> list = goodsMapper().selectVOAll(query);
        PageInfo<GoodsVO> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dtModel.getDraw());
    }

    @Nullable
    @Contract("null -> null")
    public static TGoodsType selectTypeById(Integer typeId) {
        return goodsTypeMapper().selectByPrimaryKey(typeId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TGoodsType selectTypeByIdOrFail(Integer typeId) {
        checkArgument(typeId != null && typeId > 0, "typeId illegal");
        TGoodsType goodsType = selectTypeById(typeId);
        goodsTypeNotFound(goodsType);
        return goodsType;
    }

    /**
     * 查找所有分类
     *
     * @param status 可为空，为空时查找全部
     * @return
     */
    public static List<TGoodsType> findAllTypesByStatus(@Nullable Boolean status) {
        if (status == null) {
            return goodsTypeMapper().selectAll();
        } else {
            TGoodsType goodsType = new TGoodsType();
            goodsType.setStatus(status);
            return goodsTypeMapper().select(goodsType);
        }
    }

    public static Page<TGoodsType> findGoodsTypePage(String table) {
        DtModel dtModel = JSON.parseObject(table, DtModel.class);
        PageHelper.startPage(dtModel.getPageNum(), dtModel.getLength(), dtModel.getOrderBy());
        List<TGoodsType> list = goodsTypeMapper().selectAll();
        PageInfo<TGoodsType> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dtModel.getDraw());
    }

    @Nullable
    @Contract("null -> null")
    public static TGoodsBid selectBidById(Integer bidId) {
        return goodsBidMapper().selectByPrimaryKey(bidId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TGoodsBid selectBidByIdOrFail(Integer bidId) {
        checkArgument(bidId != null && bidId > 0, "bidId illegal");
        TGoodsBid goodsBid = selectBidById(bidId);
        goodsBidNotFound(goodsBid);
        return goodsBid;
    }

    public static Page<GoodsBidVO> findGoodsBidVOPage(String table, GoodsBidQuery query) {
        DtModel dtModel = JSON.parseObject(table, DtModel.class);
        PageHelper.startPage(dtModel.getPageNum(), dtModel.getLength(), dtModel.getOrderBy());
        List<GoodsBidVO> list = goodsBidMapper().selectAllByQuery(query);
        PageInfo<GoodsBidVO> pageInfo = new PageInfo<>(list);
        return new Page<>(pageInfo, dtModel.getDraw());
    }

    public static boolean existsRunningBidRecordByGoodsId(Integer goodsId) {
        checkArgument(goodsId != null && goodsId > 0, "goodsId illegal");
        return goodsBidMapper().countByGoodsIdAndBidStatus(goodsId, BidStatus.RUNNING.getValue()) > 0;
    }

    @Contract("null -> fail")
    private static void goodsNotFound(TGoods goods) throws EntityNotFoundException {
        if (goods == null) throw new EntityNotFoundException("goods.not_found");
    }

    @Contract("null -> fail")
    private static void goodsTypeNotFound(TGoodsType goodsType) throws EntityNotFoundException {
        if (goodsType == null) throw new EntityNotFoundException("goodsType.not_found");
    }

    @Contract("null -> fail")
    private static void goodsBidNotFound(TGoodsBid goodsBid) throws EntityNotFoundException {
        if (goodsBid == null) throw new EntityNotFoundException("goodsBid.not_found");
    }

    private void initializeInjector() {
        checkState(applicationContext != null, "applicationContext");
        goodsMapper = applicationContext.getBean(TGoodsMapper.class);
        goodsTypeMapper = applicationContext.getBean(TGoodsTypeMapper.class);
        goodsBidMapper = applicationContext.getBean(TGoodsBidMapper.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initializeInjector();
    }

    public void setGoodsMapper(TGoodsMapper goodsMapper) {
        checkNotNull(goodsMapper, "goodsMapper");
        this.goodsMapper = goodsMapper;
    }

    public void setGoodsTypeMapper(TGoodsTypeMapper goodsTypeMapper) {
        checkNotNull(goodsTypeMapper, "goodsTypeMapper");
        this.goodsTypeMapper = goodsTypeMapper;
    }

    public void setGoodsBidMapper(TGoodsBidMapper goodsBidMapper) {
        checkNotNull(goodsBidMapper, "goodsBidMapper");
        this.goodsBidMapper = goodsBidMapper;
    }
}
