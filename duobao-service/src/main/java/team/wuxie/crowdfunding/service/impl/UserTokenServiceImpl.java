package team.wuxie.crowdfunding.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional(readOnly = true)
public class UserTokenServiceImpl extends AbstractService<TUserToken> implements UserTokenService {

    @Autowired
    private TUserTokenMapper userTokenMapper;

    @Override
    public TUserToken getByUserToken(String userToken) {
        return userTokenMapper.getByUserToken(userToken);
    }

    @Override
    @Transactional
    public String updateUserToken(Integer userId,Integer platform) {
        Date now = new Date();
        TUserToken userToken = selectById(userId);
        String sessionToken = DigestUtils.md5Hex(String.format("%s-%s", userId, now.getTime()));
        if (userToken == null) {
            userToken = TUserToken.create(userId, sessionToken, now, now, null, 0L,platform,
                    1, 1, SessionStatus.NORMAL.getValue(), now);
            insertSelective(userToken);
        } else {
            userToken.changeUserToken(sessionToken, now);
            userToken.setPlatform(platform);
            update(userToken);
        }
        return sessionToken;
    }
}
