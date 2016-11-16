package team.wuxie.crowdfunding.mapper;

import org.apache.ibatis.annotations.Param;
import team.wuxie.crowdfunding.domain.TShippingRecord;
import team.wuxie.crowdfunding.model.ShippingRecordQuery;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

import java.util.List;

public interface TShippingRecordMapper extends BaseMapper<TShippingRecord> {

    List<TShippingRecord> selectAllByQuery(@Param("query")ShippingRecordQuery query);
}