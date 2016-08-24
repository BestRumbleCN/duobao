package team.wuxie.crowdfunding.service;

import org.springframework.transaction.annotation.Transactional;
import team.wuxie.crowdfunding.domain.CodeType;
import team.wuxie.crowdfunding.domain.TSmsCode;
import team.wuxie.crowdfunding.util.service.BaseService;

/**
 * <p>
 * 短信验证码相关Service
 * </p>
 *
 * @author wushige
 * @date 2016-08-18 17:24
 */
public interface SmsCodeService extends BaseService<TSmsCode> {

    /**
     * 发送短信验证码
     *
     * @param cellphone 手机号
     * @param codeType  验证码类型
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    boolean sendSmsCode(String cellphone, CodeType codeType) throws IllegalArgumentException;
}
