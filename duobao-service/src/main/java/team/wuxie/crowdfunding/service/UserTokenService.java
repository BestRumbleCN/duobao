package team.wuxie.crowdfunding.service;

import team.wuxie.crowdfunding.domain.TUserToken;
import team.wuxie.crowdfunding.util.service.BaseService;

/**
 * <p>
 * 用户认证Service
 * </p>
 *
 * @author wushige
 * @date 2016-08-06 18:18
 */
public interface UserTokenService extends BaseService<TUserToken> {

    /**
     * 根据userToken查询用户token
     *
     * @param userToken
     * @return
     */
    TUserToken getByUserToken(String userToken);
}
