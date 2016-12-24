package team.wuxie.crowdfunding.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import team.wuxie.crowdfunding.domain.enums.CodeType;
import team.wuxie.crowdfunding.domain.TSmsCode;
import team.wuxie.crowdfunding.mapper.TSmsCodeMapper;
import team.wuxie.crowdfunding.service.SmsCodeService;
import team.wuxie.crowdfunding.util.IdGenerator;
import team.wuxie.crowdfunding.util.RegexUtil;
import team.wuxie.crowdfunding.util.aliyun.dayu.DayuService;
import team.wuxie.crowdfunding.util.service.AbstractService;

import java.util.Date;

/**
 * <p>
 * 短信验证码相关ServiceImpl
 * </p>
 *
 * @author wushige
 * @date 2016-08-18 17:25
 */
@Service
@Transactional(readOnly = true)
public class SmsCodeServiceImpl extends AbstractService<TSmsCode> implements SmsCodeService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	TSmsCodeMapper smsCodeMapper;

	@Override
	@Transactional
	public boolean sendSmsCode(String cellphone, CodeType codeType) throws IllegalArgumentException {
		Assert.isTrue(RegexUtil.isCellphone(cellphone), "smsCode.cellphone_format_is_wrong");
		Assert.notNull(codeType, "smsCode.codeType_cannot_be_null");

		TSmsCode smsCode = selectById(cellphone);
		Assert.isTrue(smsCode == null || smsCode.isOften() || !smsCode.getCodeType().sameValueAs(codeType),
				"smsCode.request_too_often");

		String code = String.valueOf(IdGenerator.getRandomLong(6));
		LOGGER.info(String.format("cellphone：%s，验证码：%s", cellphone, code));

		// 发送验证码
		// DayuClient client = DayuClient.getClient();
		// client.getDayuApi().sendSms(cellphone, String.format("{\"code\":
		// \"%s\"}", code));
		boolean success = false;
		switch (codeType) {
		case REGISTER:
			success = DayuService.register(cellphone, code);
			break;
		case FORGOT_PASSWORD:
			success = DayuService.chagenPsw(cellphone, code);
			break;
		case BIND_CELLPHONE:
			success = DayuService.bindCellphone(cellphone, code);
		default:
			break;
		}
		Assert.isTrue(success, "smsCode.request_too_often");
		if (smsCode == null) {
			// add
			smsCode = new TSmsCode(cellphone, code, new Date(), 0, false, codeType);
			insertSelective(smsCode);
		} else {
			// update
			smsCode.setCode(code);
			smsCode.setReceiveTime(new Date());
			smsCode.setTimes(smsCode.getTimes() + 1);
			smsCode.setVerified(false);
			smsCode.setCodeType(codeType);
			updateSelective(smsCode);
		}
        return true;
    }

    @Override
    public boolean checkSmsCode(String cellphone, String smsCode, CodeType codeType) throws IllegalArgumentException {
        TSmsCode tem = selectById(cellphone);
        Assert.isTrue(tem != null && tem.isLegal(codeType, smsCode), "smsCode.is_wrong");
        return true;
    }
}
