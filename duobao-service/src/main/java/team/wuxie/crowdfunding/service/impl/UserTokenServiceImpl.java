package team.wuxie.crowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.wuxie.crowdfunding.domain.TUserToken;
import team.wuxie.crowdfunding.mapper.TUserTokenMapper;
import team.wuxie.crowdfunding.service.UserTokenService;
import team.wuxie.crowdfunding.util.service.AbstractService;

/**
 * <p>
 * 用户认证ServiceImpl
 * </p>
 *
 * @author wushige
 * @date 2016-08-06 18:19
 */
@Service
public class UserTokenServiceImpl extends AbstractService<TUserToken> implements UserTokenService {

    @Autowired
    TUserTokenMapper tUserTokenMapper;

    @Override
    public TUserToken getByUserToken(String userToken) {
        return tUserTokenMapper.getByUserToken(userToken);
    }
}
