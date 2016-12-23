package team.wuxie.crowdfunding.domain.support;

import com.google.common.collect.ImmutableMap;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.mapper.TUserMapper;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;
import static org.springframework.util.StringUtils.hasText;

/**
 * Users: 用户相关领域模型
 *
 * @author WuGang
 * @since 1.0
 */
public class Users implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private TUserMapper userMapper;

    private Users() {
    }

    private static final Users INSTANCE = new Users();

    public static Users getInstance() {
        return INSTANCE;
    }

    public static TUserMapper userMapper() {
        return INSTANCE.userMapper;
    }

    @Contract("null -> fail")
    private static void userNotFound(TUser user) throws EntityNotFoundException {
        if (user == null) throw new EntityNotFoundException("user.not_found");
    }

    @Nullable
    @Contract("null -> null")
    public static TUser selectById(Integer userId) {
        return userMapper().selectByPrimaryKey(userId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TUser selectByIdOrFail(Integer userId) {
        checkArgument(userId != null && userId > 0, "userId illegal");
        TUser user = selectById(userId);
        userNotFound(user);
        return user;
    }

    @NotNull
    public static Map<Integer, String> findId2NameMap(@Nullable Collection<Integer> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return ImmutableMap.of();
        }
        return userMapper().findId2NameMap(userIds);
    }

    @Nullable
    public static Integer findUserIdByUsername(String username) {
        if (!hasText(username)) {
            return null;
        }
        return userMapper().selectIdByUsername(username);
    }


    public static void assignUserAware(Collection<? extends UserAware> list) {
        if (list == null || list.isEmpty()) return;
        List<Integer> userIds = new ArrayList<>(list.size());
        userIds.addAll(list.stream().filter(Objects::nonNull)
                .map((Function<UserAware, Integer>) UserAware::getUserId)
                .collect(Collectors.toList()));
        Map<Integer, String> userId2NameMap = findId2NameMap(userIds);
        for (UserAware userAware : list) {
            String username = userId2NameMap.get(userAware.getUserId());
            userAware.setUsername(username);
        }
    }

    private void initializeInjector() {
        checkState(applicationContext != null, "applicationContext");
        userMapper = applicationContext.getBean(TUserMapper.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initializeInjector();
    }

    public void setUserMapper(TUserMapper userMapper) {
        checkNotNull(userMapper, "userMapper");
        this.userMapper = userMapper;
    }
}
