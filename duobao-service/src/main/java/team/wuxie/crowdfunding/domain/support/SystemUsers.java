package team.wuxie.crowdfunding.domain.support;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import team.wuxie.crowdfunding.domain.TSystemUser;
import team.wuxie.crowdfunding.mapper.TSystemUserMapper;

import javax.persistence.EntityNotFoundException;

import static com.google.common.base.Preconditions.*;
import static org.springframework.util.StringUtils.hasText;

/**
 * SystemUsers: 管理用户相关领域模型 -- 管理员用户表
 *
 * @author WuGang
 * @since 1.0
 */
@SuppressWarnings("unused")
public class SystemUsers implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private TSystemUserMapper systemUserMapper;

    private SystemUsers() {
    }

    private static final SystemUsers INSTANCE = new SystemUsers();

    public static SystemUsers getInstance() {
        return INSTANCE;
    }

    public static TSystemUserMapper systemUserMapper() {
        return INSTANCE.systemUserMapper;
    }

    @Nullable
    @Contract("null -> null")
    public static TSystemUser selectById(Integer systemUserId) {
        return systemUserMapper().selectByPrimaryKey(systemUserId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TSystemUser selectByIdOrFail(Integer systemUserId) {
        checkArgument(systemUserId != null && systemUserId > 0, "systemUserId illegal");
        TSystemUser systemUser = selectById(systemUserId);
        systemUserNotFound(systemUser);
        return systemUser;
    }

    public static boolean existsByUsername(String username) {
        return hasText(username) && systemUserMapper().countByUsername(username) > 0;
    }

    @Contract("null -> fail")
    private static void systemUserNotFound(TSystemUser systemUser) throws EntityNotFoundException {
        if (systemUser == null) throw new EntityNotFoundException("systemUser.not_found");
    }

    private void initializeInjector() {
        checkState(applicationContext != null, "applicationContext");
        systemUserMapper = applicationContext.getBean(TSystemUserMapper.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initializeInjector();
    }

    public void setSystemUserMapper(TSystemUserMapper systemUserMapper) {
        checkNotNull(systemUserMapper, "systemUserMapper");
        this.systemUserMapper = systemUserMapper;
    }
}
