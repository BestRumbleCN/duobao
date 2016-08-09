package team.wuxie.crowdfunding.service;


import team.wuxie.crowdfunding.domain.CurrentUser;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wushige
 * @date 2016-06-30 17:38
 */
public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Integer userId);
}
