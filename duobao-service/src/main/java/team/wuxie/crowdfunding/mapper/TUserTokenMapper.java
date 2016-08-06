package team.wuxie.crowdfunding.mapper;

import team.wuxie.crowdfunding.domain.TUserToken;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

public interface TUserTokenMapper extends BaseMapper<TUserToken> {

    /**
     * 根据userToken查询用户token
     *
     * @param userToken
     * @return
     */
    TUserToken getByUserToken(String userToken);
}