package team.wuxie.crowdfunding.domain.support;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import team.wuxie.crowdfunding.domain.TArea;
import team.wuxie.crowdfunding.mapper.TAreaMapper;

import javax.persistence.EntityNotFoundException;

import static com.google.common.base.Preconditions.*;

/**
 * Areas: 地区相关领域模型 -- 涉及地区表
 *
 * @author WuGang
 * @since 1.0
 */
@SuppressWarnings("unused")
public class Areas implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private TAreaMapper areaMapper;

    private Areas() {
    }

    private static final Areas INSTANCE = new Areas();

    public static Areas getInstance() {
        return INSTANCE;
    }

    public static TAreaMapper areaMapper() {
        return INSTANCE.areaMapper;
    }

    @Nullable
    @Contract("null -> null")
    public static TArea selectById(Integer areaId) {
        return areaMapper().selectByPrimaryKey(areaId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TArea selectByIdOrFail(Integer areaId) {
        checkArgument(areaId != null && areaId > 0, "areaId illegal");
        TArea area = selectById(areaId);
        areaNotFound(area);
        return area;
    }

    @Contract("null -> fail")
    private static void areaNotFound(TArea area) throws EntityNotFoundException {
        if (area == null) throw new EntityNotFoundException("area.not_found");
    }

    private void initializeInjector() {
        checkState(applicationContext != null, "applicationContext");
        areaMapper = applicationContext.getBean(TAreaMapper.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initializeInjector();
    }

    public void setAreaMapper(TAreaMapper areaMapper) {
        checkNotNull(areaMapper, "areaMapper");
        this.areaMapper = areaMapper;
    }
}
