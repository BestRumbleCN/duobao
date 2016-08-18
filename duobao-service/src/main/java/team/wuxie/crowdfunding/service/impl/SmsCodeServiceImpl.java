package team.wuxie.crowdfunding.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import team.wuxie.crowdfunding.domain.TSmsCode;
import team.wuxie.crowdfunding.service.SmsCodeService;
import team.wuxie.crowdfunding.util.service.AbstractService;

/**
 * <p>
 * 短信验证码相关ServiceImpl
 * </p>
 *
 * @author wushige
 * @date 2016-08-18 17:25
 */
@Service
public class SmsCodeServiceImpl extends AbstractService<TSmsCode> implements SmsCodeService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());
}
