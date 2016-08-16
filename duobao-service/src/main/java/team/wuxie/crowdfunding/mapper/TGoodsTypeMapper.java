package team.wuxie.crowdfunding.mapper;

import org.apache.ibatis.annotations.Param;
import team.wuxie.crowdfunding.domain.TGoodsType;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface TGoodsTypeMapper extends BaseMapper<TGoodsType> {

    int countByTypeName(@Param("typeName") String typeName);

    List<TGoodsType> selectAllLike(Map map);

    int updateStatus(@Param("typeId") Integer typeId, @Param("status") boolean status, @Param("updateId") Integer updateId);
}