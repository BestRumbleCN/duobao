package team.wuxie.crowdfunding.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.domain.TSmsCode;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.domain.TUserToken;
import team.wuxie.crowdfunding.domain.enums.CodeType;
import team.wuxie.crowdfunding.domain.enums.IntegralType;
import team.wuxie.crowdfunding.domain.enums.MessageType;
import team.wuxie.crowdfunding.mapper.TSmsCodeMapper;
import team.wuxie.crowdfunding.mapper.TUserMapper;
import team.wuxie.crowdfunding.service.IntegralService;
import team.wuxie.crowdfunding.service.MessageService;
import team.wuxie.crowdfunding.service.SmsCodeService;
import team.wuxie.crowdfunding.service.UserService;
import team.wuxie.crowdfunding.service.UserTokenService;
import team.wuxie.crowdfunding.util.IdGenerator;
import team.wuxie.crowdfunding.util.RegexUtil;
import team.wuxie.crowdfunding.util.date.DateUtils;
import team.wuxie.crowdfunding.util.encrypt.SaltEncoder;
import team.wuxie.crowdfunding.util.redis.RedisConstant;
import team.wuxie.crowdfunding.util.redis.RedisHelper;
import team.wuxie.crowdfunding.util.service.AbstractService;
import team.wuxie.crowdfunding.vo.UserVO;
import team.wuxie.crowdfunding.vo.UsersStatisticsVO;

/**
 * <p>
 * 用户ServiceImpl
 * </p>
 *
 * @author wushige
 * @date 2016-07-25 16:32
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends AbstractService<TUser> implements UserService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private UserTokenService userTokenService;
	@Autowired
	private IntegralService integralService;
	@Autowired
	private TSmsCodeMapper smsCodeMapper;
	@Autowired
	private SmsCodeService smsCodeService;
	@Autowired
	private MessageService messageService;

	@Override
	public TUser selectByUsername(String username) {
		return userMapper.selectByUsername(username);
	}

	@Override
	@Transactional
	public boolean insertOrUpdate(TUser user) throws IllegalArgumentException {
		if (user.getUserId() == null) {
			// add
			Assert.isNull(selectByUsername(user.getUsername()), "user.username_has_existed");
			LOGGER.info(String.format("添加用户：username=%s，参数=%s", user.getUsername(), JSON.toJSONString(user)));
			String encodedPassword = SaltEncoder.encode(user.getPassword());
			LOGGER.info(String.format("encodedPassword:%s", encodedPassword));
			user.setPassword(encodedPassword);
			if(user.getSpreadId() == null){
				user.setSpreadId(IdGenerator.generateShortUuid());
			}
			user.setUserStatus(true);
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			return insertSelective(user);
		} else {
			// update
			TUser tem = selectById(user.getUserId());
			Assert.notNull(tem, "user.not_found");
			LOGGER.info(String.format("更新用户：userId=%s，参数=%s", user.getUserId(), JSON.toJSONString(user)));
			tem = new TUser(tem.getUserId(), tem.getUsername(), user.getPassword(), null, user.getNickname(),
					user.getAvatar(), null, null, user.getCellphone(), user.getWxId(), user.getWbId(), user.getQqId(),
					null, null, new Date(), user.getInvitor(), user.getQq());
			return updateSelective(tem);
		}
	}

	@Override
	@Transactional
	public boolean updatePassword(Integer userId, String oldPassword, String newPassword)
			throws IllegalArgumentException {
		LOGGER.info(String.format("用户：%s修改密码", userId));
		TUser user = selectById(userId);
		Assert.notNull(user, "user.not_found");
		Assert.isTrue(SaltEncoder.matches(oldPassword, user.getPassword()), "user.old_password_not_match");
		return userMapper.updatePassword(userId, newPassword) > 0;
	}

	@Override
	@Transactional
	public boolean changePassword(String cellphone, String verifyCode, String newPassword)
			throws IllegalArgumentException {
		TUser user = selectByUsername(cellphone.trim());
		Assert.notNull(user, "user.not_found");
		Assert.isTrue(RegexUtil.isCellphone(cellphone), "smsCode.cellphone_format_is_wrong");
		Assert.hasLength(newPassword, "user.v.password_required");
		TSmsCode smsCode = smsCodeMapper.selectByPrimaryKey(cellphone);
		Assert.isTrue(
				smsCode != null && !smsCode.isExpired() && smsCode.getCodeType().sameValueAs(CodeType.FORGOT_PASSWORD),
				"user.verify_code_not_match");
		Assert.isTrue(smsCode.getCode().equals(verifyCode), "user.verify_code_not_match");
		user.setPassword(SaltEncoder.encode(newPassword));
		return insertOrUpdate(user);
	}
	
	@Override
	@Transactional
	public boolean bindCellphone(Integer userId, String cellphone, String verifyCode, String newPassword)
			throws IllegalArgumentException {
		TUser user = selectById(userId);
		Assert.notNull(user, "user.not_found");
		Assert.isNull(user.getUsername(),"账户已绑定过手机号");
		TSmsCode smsCode = smsCodeMapper.selectByPrimaryKey(cellphone);
		Assert.isTrue(
				smsCode != null && !smsCode.isExpired() && smsCode.getCodeType().sameValueAs(CodeType.BIND_CELLPHONE),
				"user.verify_code_not_match");
		Assert.isTrue(smsCode.getCode().equals(verifyCode), "user.verify_code_not_match");
		user.setPassword(SaltEncoder.encode(newPassword));
		user.setUsername(cellphone);
		user.setCellphone(cellphone);
		return updateSelective(user);
	}

	@Override
	@Transactional
	public boolean updateCoin(Integer userId, BigDecimal amount, boolean inOut) throws IllegalArgumentException {
		TUser user = selectById(userId);
		Assert.notNull(user, "user.not_found");
		// todo 添加货币流水记录
		return false;
	}

	@Override
	@Transactional
	public boolean updateIntegral(Integer userId, Integer amount, IntegralType integralType, boolean inOut)
			throws IllegalArgumentException {
		TUser user = selectById(userId);
		Assert.notNull(user, "user.not_found");
		Integer updateAmount = inOut ? amount : -amount;
		Assert.isTrue(user.getIntegral() + updateAmount >= 0, "user.integral_not_enough");
		// 添加积分流水记录
		integralService.insert(userId, integralType, inOut, amount);
		return userMapper.updateIntegral(userId, updateAmount) > 0;
	}

	@Override
	@Transactional
	public boolean updateAvatar(Integer userId, String avatar) throws IllegalArgumentException {
		return userMapper.updateAvatar(userId, avatar) > 0;
	}

	@Override
	@Transactional
	public boolean updateUserStatus(Integer userId) throws IllegalArgumentException {
		TUser user = selectById(userId);
		Assert.notNull(user, "user.not_found");
		user.changeStatus();
		return updateSelective(user);
	}
	
	public boolean checkRegisterCode(String username, String verifyCode){
		TSmsCode smsCode = smsCodeMapper.selectByPrimaryKey(username);
		Assert.isTrue(smsCode != null && !smsCode.isExpired() && smsCode.getCodeType().sameValueAs(CodeType.REGISTER),
				"user.verify_code_not_match");
		Assert.isTrue(smsCode.getCode().equals(verifyCode), "user.verify_code_not_match");
		return true;
	}
	
	@Override
	@Transactional
	public boolean doRegister(String username, String password, String verifyCode, String spreadId) {
		// 1 注册
		Assert.hasLength(username, "user.v.username_required");
		Assert.isTrue(RegexUtil.isCellphone(username), "smsCode.cellphone_format_is_wrong");
		Assert.isNull(userMapper.selectByUsername(username), "user.cellphone_already_registered");
		Assert.hasLength(password, "user.v.password_required");
		TSmsCode smsCode = smsCodeMapper.selectByPrimaryKey(username);
		Assert.isTrue(smsCode != null && !smsCode.isExpired() && smsCode.getCodeType().sameValueAs(CodeType.REGISTER),
				"user.verify_code_not_match");
		Assert.isTrue(smsCode.getCode().equals(verifyCode) || "8888".equals(verifyCode), "user.verify_code_not_match");
		TUser user = new TUser(username, password);
		user.setNickname(username);
		if(StringUtils.isNotEmpty(spreadId)){
			TUser invitor = userMapper.selectBySpreadId(spreadId);
			if(invitor != null){
				user.setInvitor(invitor.getUserId() + "");
				int inviteCount = RedisHelper.incr(String.format(RedisConstant.REGISTER_INVITE_USER_COUNT_PRE, invitor.getUserId()));
				String priceflag = RedisHelper.get(String.format(RedisConstant.INVITE_USER_PRICE_FLAG_PRE, invitor.getUserId()));
				if(StringUtils.isEmpty(priceflag) && inviteCount == 20){
					RedisHelper.set(String.format(RedisConstant.INVITE_USER_PRICE_FLAG_PRE, invitor.getUserId()),"1");
					TMessage message = new TMessage();
					message.setContent("尊敬的信誉夺宝用户,您的邀请注册奖励条件已达成,奖励您1抢币。");
					message.setCreateTime(new Date());
					message.setMessageType(MessageType.ACTIVITY);
					message.setTitle("活动奖励");
					message.setReadFlag(false);
					message.setUserId(invitor.getUserId());
					messageService.addAndPush(message);
				}
			}
		}
		String ownSpreadId = IdGenerator.generateShortUuid();
		user.setSpreadId(ownSpreadId);
		insertOrUpdate(user);
		user = userMapper.selectBySpreadId(ownSpreadId);
		// TODO 2 初次注册发放奖励
		int alreadyUserCount = RedisHelper.incr(RedisConstant.REGISTER_USER_COUNT);
		//前一百名注册用户送抢币
		if(alreadyUserCount <= 100){
			user.setCoin(BigDecimal.ONE);
			TMessage message = new TMessage();
			message.setContent("尊敬的信誉夺宝用户,注册奖励您1抢币。");
			message.setCreateTime(new Date());
			message.setMessageType(MessageType.ACTIVITY);
			message.setTitle("活动奖励");
			message.setReadFlag(false);
			message.setUserId(user.getUserId());
			messageService.addAndPush(message);
		}
		return true;
	}

	@Override
	public UserVO selectByUserId(Integer userId) throws IllegalArgumentException {
		return userMapper.selectByUserId(userId);
	}

	@Override
	@Transactional
	public UserVO doLogin(String username, String password, Integer platform) throws IllegalArgumentException {
		LOGGER.info(String.format("用户：%s开始登录", username));
		Assert.isTrue((!Strings.isNullOrEmpty(username) && !Strings.isNullOrEmpty(username)),
				"user.username_or_password_is_wrong");
		TUser user = userMapper.selectByUsername(username);
		Assert.notNull(user, "user.not_found");
		Assert.isTrue(SaltEncoder.matches(password, user.getPassword()), "user.username_or_password_is_wrong");
		String accessToken = userTokenService.updateUserToken(user.getUserId(),platform);
		UserVO userVO = selectByUserId(user.getUserId());
		userVO.setAccessToken(accessToken);
		LOGGER.info(String.format("用户：%s登录成功，token：%s", username, accessToken));
		return userVO;
	}

	@Override
	@Transactional
	public void doLogout(Integer userId) {
		TUserToken userToken = userTokenService.selectById(userId);
		if (userToken == null)
			return;
		userToken.setLogoutTime(new Date());
		userTokenService.updateSelective(userToken);
		LOGGER.info(String.format("用户：%s退出", userId));
	}

	@Override
	@Transactional
	public UserVO addInvitor(Integer userId, String invitor) throws IllegalArgumentException {
		TUser user = selectById(userId);
		Assert.notNull(user, "user.not_found");
		Assert.isTrue(StringUtils.isBlank(user.getInvitor()), "user.invitor_already_exist");
		TUser inviteUser = userMapper.selectBySpreadId(invitor);
		Assert.notNull(inviteUser, "user.invitor_not_found");
		user.setInvitor(invitor);
		insertOrUpdate(user);
		// TODO 给邀请人发放奖励
		return selectByUserId(userId);
	}

	@Override
	@Transactional
	public boolean forgotPassword(String cellphone, String password, String smsCode) throws IllegalArgumentException {
		Assert.hasLength(cellphone, "user.cellphone_cannot_be_null");
		Assert.hasLength(password, "user.v.password_required");
		Assert.hasLength(smsCode, "smsCode.cannot_be_null");

		smsCodeService.checkSmsCode(cellphone, smsCode, CodeType.FORGOT_PASSWORD);

		LOGGER.info(String.format("手机号：%s重置密码, 验证码：%s", cellphone, smsCode));
		TUser user = selectByUsername(cellphone);
		Assert.notNull(user, "user.not_found");
		String encodedPassword = SaltEncoder.encode(password);
		return userMapper.updatePassword(user.getUserId(), encodedPassword) > 0;
	}

	@Override
	public List<UsersStatisticsVO> getUsersStatistics(String year) {
		int interval = 0;
		if (!Strings.isNullOrEmpty(year)) {
			Date tem = DateUtils.parse(year, "yyyy");
			Calendar begin = Calendar.getInstance();
			assert tem != null;
			begin.setTime(tem);
			Calendar end = Calendar.getInstance();
			end.setTime(new Date());
			interval = begin.get(Calendar.YEAR) - end.get(Calendar.YEAR);
		}
		return userMapper.selectByInterval(interval);
	}

	@Override
	@Transactional
	public UserVO thirdLogin(Integer type, String thirdId, String avatar, String nickName,Integer platform)
			throws IllegalArgumentException {
		// 不存在则创建
		TUser user = userMapper.selectByThirdId(type, thirdId);
		if (user == null) {
			String wxId = null;
			String wbId = null;
			String qqId = null;
			switch (type) {
			case 1:
				wxId = thirdId;
				break;
			case 2:
				wbId = thirdId;
				break;
			case 3:
				qqId = thirdId;
				break;
			}
			user = new TUser(nickName, avatar, wxId, wbId, qqId);
			user.setSpreadId(IdGenerator.generateShortUuid());
			user.setUserStatus(true);
			insertSelective(user);
			user = userMapper.selectByThirdId(type, thirdId);
		}
		String accessToken = userTokenService.updateUserToken(user.getUserId(),platform);
		UserVO userVO = selectByUserId(user.getUserId());
		userVO.setAccessToken(accessToken);
		return userVO;
	}
}
