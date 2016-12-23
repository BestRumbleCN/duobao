package team.wuxie.crowdfunding.domain.support;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import team.wuxie.crowdfunding.domain.TLuckyShare;
import team.wuxie.crowdfunding.mapper.TLuckyShareMapper;

import javax.persistence.EntityNotFoundException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * LuckyShares: 幸运分享相关领域模型 -- 涉及幸运分享表
 *
 * @author WuGang
 * @since 1.0
 */
@SuppressWarnings("unused")
public class LuckyShares implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private TLuckyShareMapper luckyShareMapper;

    private LuckyShares() {
    }

    private static final LuckyShares INSTANCE = new LuckyShares();

    public static LuckyShares getInstance() {
        return INSTANCE;
    }

    public static TLuckyShareMapper luckyShareMapper() {
        return INSTANCE.luckyShareMapper;
    }

    @Nullable
    @Contract("null -> null")
    public static TLuckyShare selectById(Integer luckyShareId) {
        return luckyShareMapper().selectByPrimaryKey(luckyShareId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TLuckyShare selectByIdOrFail(Integer luckyShareId) {
        checkArgument(luckyShareId != null && luckyShareId > 0, "luckyShareId illegal");
        TLuckyShare luckyShare = selectById(luckyShareId);
        luckyShareNotFound(luckyShare);
        return luckyShare;
    }

    @Contract("null -> fail")
    private static void luckyShareNotFound(TLuckyShare luckyShare) throws EntityNotFoundException {
        if (luckyShare == null) throw new EntityNotFoundException("luckyShare.not_found");
    }

    private void initializeInjector() {
        checkState(applicationContext != null, "applicationContext");
        luckyShareMapper = applicationContext.getBean(TLuckyShareMapper.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initializeInjector();
    }

    public void setLuckyShareMapper(TLuckyShareMapper luckyShareMapper) {
        checkNotNull(luckyShareMapper, "luckyShareMapper");
        this.luckyShareMapper = luckyShareMapper;
    }
}
