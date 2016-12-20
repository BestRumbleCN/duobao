package team.wuxie.crowdfunding.domain;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.authority.AuthorityUtils;
import team.wuxie.crowdfunding.domain.enums.Role;

/**
 * <p>
 * 当前用户属性
 * </p>
 *
 * @author wushige
 * @date 2016-06-30 17:01
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private TSystemUser systemUser;

    public CurrentUser(TSystemUser systemUser) {
        super(systemUser.getUsername(), systemUser.getPassword(), AuthorityUtils.createAuthorityList(Role.ADMIN.toString()));
        this.systemUser = systemUser;
    }

    public TSystemUser getSystemUser() {
        return systemUser;
    }

    public Integer getUserId() {
        return systemUser.getUserId();
    }

    public Role getRole() {
        return Role.ADMIN;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
