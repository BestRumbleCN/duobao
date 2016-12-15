package team.wuxie.crowdfunding.mapper;

import org.apache.ibatis.annotations.Param;
import team.wuxie.crowdfunding.domain.TGoods;
import team.wuxie.crowdfunding.model.GoodsQuery;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;
import team.wuxie.crowdfunding.vo.GoodsVO;

import java.util.List;
import java.util.Map;

public interface TGoodsMapper extends BaseMapper<TGoods> {

    int countByGoodsName(@Param("goodsName") String goodsName);

    int updateGoodsStatus(@Param("goodsId") Integer goodsId, @Param("goodsStatus") boolean goodsStatus);



    //GoodsVO相关
    List<GoodsVO> selectVOAll(@Param("query") GoodsQuery query);

    List<GoodsVO> selectVOAllLike(Map map);
    
    List<GoodsVO> selectVOs(Map map);
}