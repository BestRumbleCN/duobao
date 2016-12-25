package team.wuxie.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.wuxie.crowdfunding.domain.TShippingRecord;
import team.wuxie.crowdfunding.mapper.TShippingRecordMapper;
import team.wuxie.crowdfunding.model.ShippingRecordQuery;
import team.wuxie.crowdfunding.service.ShippingRecordService;
import team.wuxie.crowdfunding.util.service.AbstractService;

import java.util.List;

/**
 * @author WuGang
 * @since 1.0
 */
@Service
@Transactional(readOnly = true)
public class ShippingRecordServiceImpl extends AbstractService<TShippingRecord> implements ShippingRecordService {

    @Autowired
    private TShippingRecordMapper shippingRecordMapper;

    @Override
    public List<TShippingRecord> selectAll(ShippingRecordQuery query) {
        return shippingRecordMapper.selectAllByQuery(query);
    }
}
