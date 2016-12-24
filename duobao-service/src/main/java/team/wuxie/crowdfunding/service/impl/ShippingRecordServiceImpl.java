package team.wuxie.crowdfunding.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    TShippingRecordMapper tShippingRecordMapper;

    @Override
    public List<TShippingRecord> selectAll(ShippingRecordQuery query) {
        return tShippingRecordMapper.selectAllByQuery(query);
    }
}
