package team.wuxie.crowdfunding.domain;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.authority.AuthorityUtils;
import team.wuxie.crowdfunding.util.fastjson.SerializerFeatures;

/**
 * <p>
 * 当前用户属性
 * </p>
 *
 * @author wushige
 * @date 2016-06-30 17:01
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getUserId() {
        return user.getUserId();
    }

    public String getRole() {
        return user.getRole();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeatures.features);
    }
}
