package team.wuxie.crowdfunding.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.wuxie.crowdfunding.domain.TSystemUser;
import team.wuxie.crowdfunding.mapper.TSystemUserMapper;
import team.wuxie.crowdfunding.service.SystemUserService;
import team.wuxie.crowdfunding.util.service.AbstractService;

/**
 * <p>
 * 系统用户相关ServiceImpl
 * </p>
 *
 * @author wushige
 * @date 2016-08-17 14:57
 */
@Service
public class SystemUserServiceImpl extends AbstractService<TSystemUser> implements SystemUserService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    TSystemUserMapper systemUserMapper;

    @Override
    public TSystemUser selectByUsername(String username) {
        return systemUserMapper.selectByUsername(username);
    }
}
