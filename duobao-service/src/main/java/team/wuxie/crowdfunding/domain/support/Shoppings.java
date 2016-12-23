package team.wuxie.crowdfunding.domain.support;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import team.wuxie.crowdfunding.domain.TShoppingCart;
import team.wuxie.crowdfunding.domain.TShoppingLog;
import team.wuxie.crowdfunding.mapper.TShoppingCartMapper;
import team.wuxie.crowdfunding.mapper.TShoppingLogMapper;

import javax.persistence.EntityNotFoundException;

import static com.google.common.base.Preconditions.*;

/**
 * Shoppings: 购买相关领域模型 -- 涉及购物车表、购买记录表
 * @author WuGang
 * @since 1.0
 */
@SuppressWarnings("unused")
public class Shoppings implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private TShoppingCartMapper shoppingCartMapper;
    private TShoppingLogMapper shoppingLogMapper;

    private Shoppings() {
    }

    private static final Shoppings INSTANCE = new Shoppings();

    public static Shoppings getInstance() {
        return INSTANCE;
    }

    public static TShoppingCartMapper shoppingCartMapper() {
        return INSTANCE.shoppingCartMapper;
    }

    public static TShoppingLogMapper shoppingLogMapper() {
        return INSTANCE.shoppingLogMapper;
    }

    @Nullable
    @Contract("null -> null")
    public static TShoppingCart selectCartById(Integer cartId) {
        return shoppingCartMapper().selectByPrimaryKey(cartId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TShoppingCart selectAddressByIdOrFail(Integer cartId) {
        checkArgument(cartId != null && cartId > 0, "cartId illegal");
        TShoppingCart shoppingCart = selectCartById(cartId);
        cartNotFound(shoppingCart);
        return shoppingCart;
    }

    @Nullable
    @Contract("null -> null")
    public static TShoppingLog selectLogById(Integer logId) {
        return shoppingLogMapper().selectByPrimaryKey(logId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TShoppingLog selectLogByIdOrFail(Integer logId) {
        checkArgument(logId != null && logId > 0, "logId illegal");
        TShoppingLog shoppingLog = selectLogById(logId);
        logNotFound(shoppingLog);
        return shoppingLog;
    }

    @Contract("null -> fail")
    private static void cartNotFound(TShoppingCart shoppingCart) throws EntityNotFoundException {
        if (shoppingCart == null) throw new EntityNotFoundException("shoppingCart.not_found");
    }

    @Contract("null -> fail")
    private static void logNotFound(TShoppingLog shoppingLog) throws EntityNotFoundException {
        if (shoppingLog == null) throw new EntityNotFoundException("shoppingLog.not_found");
    }

    private void initializeInjector() {
        checkState(applicationContext != null, "applicationContext");
        shoppingCartMapper = applicationContext.getBean(TShoppingCartMapper.class);
        shoppingLogMapper = applicationContext.getBean(TShoppingLogMapper.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initializeInjector();
    }

    public void setShippingAddressMapper(TShoppingCartMapper shoppingCartMapper) {
        checkNotNull(shoppingCartMapper, "shoppingCartMapper");
        this.shoppingCartMapper = shoppingCartMapper;
    }

    public void setShoppingLogMapper(TShoppingLogMapper shoppingLogMapper) {
        checkNotNull(shoppingLogMapper, "shoppingLogMapper");
        this.shoppingLogMapper = shoppingLogMapper;
    }
}
