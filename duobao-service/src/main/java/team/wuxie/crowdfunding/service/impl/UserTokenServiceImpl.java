package team.wuxie.crowdfunding.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.wuxie.crowdfunding.domain.enums.SessionStatus;
import team.wuxie.crowdfunding.domain.TUserToken;
import team.wuxie.crowdfunding.mapper.TUserTokenMapper;
import team.wuxie.crowdfunding.service.UserTokenService;
import team.wuxie.crowdfunding.util.service.AbstractService;

import java.util.Date;

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

    @Override
    public String updateUserToken(Integer userId) {
        Date now = new Date();
        TUserToken userToken = selectById(userId);
        String sessionToken = DigestUtils.md5Hex(String.format("%s-%s", userId, now.getTime()));
        if (userToken == null) {
            userToken = TUserToken.create(userId, sessionToken, now, now, null, 0L,
                    1, 1, SessionStatus.NORMAL.getValue(), now);
            insertSelective(userToken);
        } else {
            userToken.changeUserToken(sessionToken, now);
            update(userToken);
        }
        return sessionToken;
    }
}
