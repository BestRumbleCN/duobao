package team.wuxie.crowdfunding.mapper;

import team.wuxie.crowdfunding.domain.TSystemUser;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

public interface TSystemUserMapper extends BaseMapper<TSystemUser> {

    TSystemUser selectByUsername(String username);
}