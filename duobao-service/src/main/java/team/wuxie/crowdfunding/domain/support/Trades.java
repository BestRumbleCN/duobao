package team.wuxie.crowdfunding.domain.support;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import team.wuxie.crowdfunding.domain.TTrade;
import team.wuxie.crowdfunding.mapper.TTradeMapper;

import javax.persistence.EntityNotFoundException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Trades: 交易记录相关领域模型 -- 交易记录表
 *
 * @author WuGang
 * @since 1.0
 */
@SuppressWarnings("unused")
public class Trades implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private TTradeMapper tradeMapper;

    private Trades() {
    }

    private static final Trades INSTANCE = new Trades();

    public static Trades getInstance() {
        return INSTANCE;
    }

    @Nullable
    @Contract("null -> null")
    public static TTrade selectById(Integer tradeId) {
        return tradeMapper().selectByPrimaryKey(tradeId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TTrade selectByIdOrFail(Integer tradeId) {
        checkArgument(tradeId != null && tradeId > 0, "tradeId illegal");
        TTrade trade = selectById(tradeId);
        tradeNotFound(trade);
        return trade;
    }

    public static TTradeMapper tradeMapper() {
        return INSTANCE.tradeMapper;
    }

    private void initializeInjector() {
        checkState(applicationContext != null, "applicationContext");
        tradeMapper = applicationContext.getBean(TTradeMapper.class);
    }

    @Contract("null -> fail")
    private static void tradeNotFound(TTrade trade) throws EntityNotFoundException {
        if (trade == null) throw new EntityNotFoundException("trade.not_found");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initializeInjector();
    }

    public void setTradeMapper(TTradeMapper tradeMapper) {
        checkNotNull(tradeMapper, "tradeMapper");
        this.tradeMapper = tradeMapper;
    }
}
