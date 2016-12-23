package team.wuxie.crowdfunding.domain.support;

import com.google.common.collect.ImmutableMap;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import team.wuxie.crowdfunding.domain.TIntegral;
import team.wuxie.crowdfunding.domain.TRedPocket;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.domain.TUserToken;
import team.wuxie.crowdfunding.mapper.TIntegralMapper;
import team.wuxie.crowdfunding.mapper.TRedPocketMapper;
import team.wuxie.crowdfunding.mapper.TUserMapper;
import team.wuxie.crowdfunding.mapper.TUserTokenMapper;

import javax.persistence.EntityNotFoundException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;
import static org.springframework.util.StringUtils.hasText;

/**
 * Users: 用户相关领域模型 -- 涉及用户表、用户token表、用户积分记录表、用户红包记录表
 *
 * @author WuGang
 * @since 1.0
 */
@SuppressWarnings("unused")
public class Users implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private TUserMapper userMapper;
    private TUserTokenMapper userTokenMapper;
    private TIntegralMapper integralMapper;
    private TRedPocketMapper redPocketMapper;

    private Users() {
    }

    private static final Users INSTANCE = new Users();

    public static Users getInstance() {
        return INSTANCE;
    }

    public static TUserMapper userMapper() {
        return INSTANCE.userMapper;
    }

    public static TUserTokenMapper userTokenMapper() {
        return INSTANCE.userTokenMapper;
    }

    public static TIntegralMapper integralMapper() {
        return INSTANCE.integralMapper;
    }

    public static TRedPocketMapper redPocketMapper() {
        return INSTANCE.redPocketMapper;
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

    public static boolean existsByUsername(String username) {
        return hasText(username) && userMapper().countByUsername(username) > 0;
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

    @Nullable
    @Contract("null -> null")
    public static TUserToken selectUserTokenById(Integer userId) {
        return userTokenMapper().selectByPrimaryKey(userId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TUserToken selectUserTokenByIdOrFail(Integer userId) {
        checkArgument(userId != null && userId > 0, "userId illegal");
        TUserToken userToken = selectUserTokenById(userId);
        userTokenNotFound(userToken);
        return userToken;
    }

    @Nullable
    @Contract("null -> null")
    public static TIntegral selectIntegralById(Integer integralId) {
        return integralMapper().selectByPrimaryKey(integralId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TIntegral selectIntegralByIdOrFail(Integer integralId) {
        checkArgument(integralId != null && integralId > 0, "integralId illegal");
        TIntegral integral = selectIntegralById(integralId);
        integralNotFound(integral);
        return integral;
    }

    @Nullable
    @Contract("null -> null")
    public static TRedPocket selectRedPocketById(Integer pocketId) {
        return redPocketMapper().selectByPrimaryKey(pocketId);
    }

    @NotNull
    @Contract("null -> fail")
    public static TRedPocket selectRedPocketByIdOrFail(Integer pocketId) {
        checkArgument(pocketId != null && pocketId > 0, "pocketId illegal");
        TRedPocket redPocket = selectRedPocketById(pocketId);
        redPocketNotFound(redPocket);
        return redPocket;
    }

    @Contract("null -> fail")
    private static void userNotFound(TUser user) throws EntityNotFoundException {
        if (user == null) throw new EntityNotFoundException("user.not_found");
    }

    @Contract("null -> fail")
    private static void userTokenNotFound(TUserToken userToken) throws EntityNotFoundException {
        if (userToken == null) throw new EntityNotFoundException("userToken.not_found");
    }

    @Contract("null -> fail")
    private static void integralNotFound(TIntegral integral) throws EntityNotFoundException {
        if (integral == null) throw new EntityNotFoundException("integral.not_found");
    }

    @Contract("null -> fail")
    private static void redPocketNotFound(TRedPocket redPocket) throws EntityNotFoundException {
        if (redPocket == null) throw new EntityNotFoundException("redPocket.not_found");
    }

    private void initializeInjector() {
        checkState(applicationContext != null, "applicationContext");
        userMapper = applicationContext.getBean(TUserMapper.class);
        userTokenMapper = applicationContext.getBean(TUserTokenMapper.class);
        integralMapper = applicationContext.getBean(TIntegralMapper.class);
        redPocketMapper = applicationContext.getBean(TRedPocketMapper.class);
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

    public void setUserTokenMapper(TUserTokenMapper userTokenMapper) {
        checkNotNull(userTokenMapper, "userTokenMapper");
        this.userTokenMapper = userTokenMapper;
    }

    public void setIntegralMapper(TIntegralMapper integralMapper) {
        checkNotNull(integralMapper, "integralMapper");
        this.integralMapper = integralMapper;
    }

    public void setRedPocketMapper(TRedPocketMapper redPocketMapper) {
        checkNotNull(redPocketMapper, "redPocketMapper");
        this.redPocketMapper = redPocketMapper;
    }
}
