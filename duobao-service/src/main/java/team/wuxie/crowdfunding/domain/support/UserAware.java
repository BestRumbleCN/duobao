package team.wuxie.crowdfunding.domain.support;

/**
 * @author WuGang
 * @since 1.0
 */
public interface UserAware {
    /**
     * 用户ID
     */
    String PROP_USER_ID = "userId";
    /**
     * 用户名
     */
    String PROP_USERNAME = "username";
    /**
     * 用户昵称
     */
    String PROP_NICKNAME = "nickname";
    /**
     * 用户头像
     */
    String PROP_AVATAR = "avatar";

    Integer getUserId();

    default String getUseruame() {
        return null;
    }

    default void setUsername(String username) {

    }

    default String getNickname() {
        return null;
    }

    default String getAvatar() {
        return null;
    }

}
