package team.wuxie.crowdfunding.util.mybatis.mapper;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用mapper
 * Created by wushige on 4/19/2016 0019.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, ConditionMapper {
}
