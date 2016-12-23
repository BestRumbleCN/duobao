package team.wuxie.crowdfunding.domain.support;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import team.wuxie.crowdfunding.domain.TShippingAddress;
import team.wuxie.crowdfunding.domain.TShippingRecord;
import team.wuxie.crowdfunding.mapper.TShippingAddressMapper;
import team.wuxie.crowdfunding.mapper.TShippingRecordMapper;

import javax.persistence.EntityNotFoundException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Shippings: 发货相关领域模型 -- 涉及发货地址表、发货记录表
 *
 * @author WuGang
 * @since 1.0
 */
@SuppressWarnings("unused")
public class Shippings implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private TShippingAddressMapper shippingAddressMapper;
    private TShippingRecordMapper shippingRecordMapper;

    private Shippings() {
    }

    private static final Shippings INSTANCE = new Shippings();

    public static Shippings getInstance() {
        return INSTANCE;
    }

    public static TShippingAddressMapper shippingAddressMapper() {
        return INSTANCE.shippingAddressMapper;
    }

    public static TShippingRecordMapper shippingRecordMapper() {
        return INSTANCE.shippingRecordMapper;
    }

    @Nullable
    @Contract("null -> null")
    public static TShippingAddress selectAddressById(Integer addressId) {
        return shippingAddressMapper().selectByPrimaryKey(addressId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TShippingAddress selectAddressByIdOrFail(Integer addressId) {
        checkArgument(addressId != null && addressId > 0, "addressId illegal");
        TShippingAddress shippingAddress = selectAddressById(addressId);
        addressNotFound(shippingAddress);
        return shippingAddress;
    }

    @Nullable
    @Contract("null -> null")
    public static TShippingRecord selectRecordById(Integer recordId) {
        return shippingRecordMapper().selectByPrimaryKey(recordId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TShippingRecord selectRecordByIdOrFail(Integer recordId) {
        checkArgument(recordId != null && recordId > 0, "recordId illegal");
        TShippingRecord shippingRecord = selectRecordById(recordId);
        recordNotFound(shippingRecord);
        return shippingRecord;
    }

    @Contract("null -> fail")
    private static void addressNotFound(TShippingAddress shippingAddress) throws EntityNotFoundException {
        if (shippingAddress == null) throw new EntityNotFoundException("shippingAddress.not_found");
    }

    @Contract("null -> fail")
    private static void recordNotFound(TShippingRecord shippingRecord) throws EntityNotFoundException {
        if (shippingRecord == null) throw new EntityNotFoundException("shippingRecord.not_found");
    }

    private void initializeInjector() {
        checkState(applicationContext != null, "applicationContext");
        shippingAddressMapper = applicationContext.getBean(TShippingAddressMapper.class);
        shippingRecordMapper = applicationContext.getBean(TShippingRecordMapper.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initializeInjector();
    }

    public void setShippingAddressMapper(TShippingAddressMapper shippingAddressMapper) {
        checkNotNull(shippingAddressMapper, "shippingAddressMapper");
        this.shippingAddressMapper = shippingAddressMapper;
    }

    public void setShippingRecordMapper(TShippingRecordMapper shippingRecordMapper) {
        checkNotNull(shippingRecordMapper, "shippingRecordMapper");
        this.shippingRecordMapper = shippingRecordMapper;
    }
}
