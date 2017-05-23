package team.wuxie.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.domain.TShippingAddress;
import team.wuxie.crowdfunding.domain.TShippingRecord;
import team.wuxie.crowdfunding.domain.support.Shippings;
import team.wuxie.crowdfunding.event.message.MessageSendingEvent;
import team.wuxie.crowdfunding.mapper.TShippingAddressMapper;
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
@Transactional(readOnly = false)
public class ShippingRecordServiceImpl extends AbstractService<TShippingRecord> implements ShippingRecordService, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private TShippingRecordMapper shippingRecordMapper;
    
    @Autowired
    private TShippingAddressMapper shippingAddressMapper;

    @Override
    public List<TShippingRecord> selectAll(ShippingRecordQuery query) {
        return shippingRecordMapper.selectAllByQuery(query);
    }

    @Override
    @Transactional
    public void deliver(Integer recordId, TMessage message) {
        TShippingRecord shippingRecord = Shippings.selectRecordByIdOrFail(recordId);
        shippingRecord.deliver();
        updateSelective(shippingRecord);

        MessageSendingEvent event = new MessageSendingEvent(message);
        applicationContext.publishEvent(event);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

	@Override
	public void updateReceiveInfo(Integer recordId) {
		TShippingRecord shippingRecord = Shippings.selectRecordByIdOrFail(recordId);
		TShippingAddress address = shippingAddressMapper.selectDefaultByUserId(shippingRecord.getUserId());
		if(address != null){
			shippingRecord.setReceiverName(address.getName());
			shippingRecord.setShippingAddress(address.getAddress());
			shippingRecord.setCellphone(address.getCellphone());
		}
		shippingRecordMapper.updateByPrimaryKeySelective(shippingRecord);
	}
}
