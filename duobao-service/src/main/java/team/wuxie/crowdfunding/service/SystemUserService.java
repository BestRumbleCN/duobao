package team.wuxie.crowdfunding.service;

import team.wuxie.crowdfunding.domain.TSystemUser;
import team.wuxie.crowdfunding.util.service.BaseService;

/**
 * <p>
 * 系统用户相关Service
 * </p>
 *
 * @author wushige
 * @date 2016-08-17 14:55
 */
public interface SystemUserService extends BaseService<TSystemUser> {

    TSystemUser selectByUsername(String username);
}
