package team.wuxie.crowdfunding.service;

import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.domain.TShippingRecord;
import team.wuxie.crowdfunding.model.ShippingRecordQuery;
import team.wuxie.crowdfunding.util.service.BaseService;

import java.util.List;

/**
 * 购买记录
 *
 * @author WuGang
 * @since 1.0
 */
public interface ShippingRecordService extends BaseService<TShippingRecord> {

    List<TShippingRecord> selectAll(ShippingRecordQuery query);

    /**
     * 发货
     *
     * @param recordId
     * @param message
     */
    void deliver(Integer recordId, TMessage message);
}
