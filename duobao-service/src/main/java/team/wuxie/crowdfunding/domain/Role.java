package team.wuxie.crowdfunding.domain;

import com.google.common.base.Strings;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author wushige
 * @date 2016-08-08 17:03
 */
public enum Role {
    ADMIN, USER;

    public static Role of(String name) {
        if (Strings.isNullOrEmpty(name)) return USER;
        for (Role role : values()) {
            if (name.equals(role.name())) return role;
        }
        return USER;
    }
}
