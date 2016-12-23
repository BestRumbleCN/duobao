package team.wuxie.crowdfunding.domain.support;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import team.wuxie.crowdfunding.domain.TBanner;
import team.wuxie.crowdfunding.mapper.TBannerMapper;

import javax.persistence.EntityNotFoundException;

import static com.google.common.base.Preconditions.*;

/**
 * Banners: Banner相关领域模型 -- 涉及Banner表
 *
 * @author WuGang
 * @since 1.0
 */
@SuppressWarnings("unused")
public class Banners implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private TBannerMapper bannerMapper;

    private Banners() {
    }

    private static final Banners INSTANCE = new Banners();

    public static Banners getInstance() {
        return INSTANCE;
    }

    public static TBannerMapper bannerMapper() {
        return INSTANCE.bannerMapper;
    }

    @Nullable
    @Contract("null -> null")
    public static TBanner selectById(Integer bannerId) {
        return bannerMapper().selectByPrimaryKey(bannerId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TBanner selectByIdOrFail(Integer bannerId) {
        checkArgument(bannerId != null && bannerId > 0, "bannerId illegal");
        TBanner banner = selectById(bannerId);
        bannerNotFound(banner);
        return banner;
    }

    @Contract("null -> fail")
    private static void bannerNotFound(TBanner banner) throws EntityNotFoundException {
        if (banner == null) throw new EntityNotFoundException("banner.not_found");
    }

    private void initializeInjector() {
        checkState(applicationContext != null, "applicationContext");
        bannerMapper = applicationContext.getBean(TBannerMapper.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initializeInjector();
    }

    public void setBannerMapper(TBannerMapper bannerMapper) {
        checkNotNull(bannerMapper, "bannerMapper");
        this.bannerMapper = bannerMapper;
    }
}
