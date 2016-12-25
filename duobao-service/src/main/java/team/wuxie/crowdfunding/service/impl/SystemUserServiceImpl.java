package team.wuxie.crowdfunding.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional(readOnly = true)
public class SystemUserServiceImpl extends AbstractService<TSystemUser> implements SystemUserService {

    @Autowired
    private TSystemUserMapper systemUserMapper;

    @Override
    public TSystemUser selectByUsername(String username) {
        return systemUserMapper.selectByUsername(username);
    }
}
