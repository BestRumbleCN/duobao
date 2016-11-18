package team.wuxie.crowdfunding.mapper;

import org.apache.ibatis.annotations.Param;
import team.wuxie.crowdfunding.domain.TActivity;
import team.wuxie.crowdfunding.model.ActivityQuery;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

import java.util.List;

public interface TActivityMapper extends BaseMapper<TActivity> {

    List<TActivity> selectAllByQuery(@Param("query")ActivityQuery query);
}