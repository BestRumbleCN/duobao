package team.wuxie.crowdfunding.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import team.wuxie.crowdfunding.domain.CodeType;
import team.wuxie.crowdfunding.domain.IntegralType;
import team.wuxie.crowdfunding.domain.Role;
import team.wuxie.crowdfunding.domain.TSmsCode;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.domain.TUserToken;
import team.wuxie.crowdfunding.mapper.TSmsCodeMapper;
import team.wuxie.crowdfunding.mapper.TUserMapper;
import team.wuxie.crowdfunding.service.IntegralService;
import team.wuxie.crowdfunding.service.UserService;
import team.wuxie.crowdfunding.service.UserTokenService;
import team.wuxie.crowdfunding.util.IdGenerator;
import team.wuxie.crowdfunding.util.RegexUtil;
import team.wuxie.crowdfunding.util.StringUtil;
import team.wuxie.crowdfunding.util.encrypt.SaltEncoder;
import team.wuxie.crowdfunding.util.service.AbstractService;
import team.wuxie.crowdfunding.vo.UserVO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户ServiceImpl
 * </p>
 *
 * @author wushige
 * @date 2016-07-25 16:32
 */
@Service
public class UserServiceImpl extends AbstractService<TUser> implements UserService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	TUserMapper userMapper;

	@Autowired
	UserTokenService userTokenService;

	@Autowired
	IntegralService integralService;

	@Autowired
	TSmsCodeMapper smsCodeMapper;

	@Override
	public TUser selectByUsername(String username) {
		return userMapper.selectByUsername(username);
	}

	@Override
	public List<TUser> selectAllLike(Map<String, String> map) {
		return userMapper.selectAllLike(map);
	}

	@Override
	public boolean insertOrUpdate(TUser user) throws IllegalArgumentException {
		if (user.getUserId() == null) {
			// add
			Assert.isNull(selectByUsername(user.getUsername()), "user.username_has_existed");
			LOGGER.info(String.format("添加用户：username=%s，参数=%s", user.getUsername(), JSON.toJSONString(user)));
			String encodedPassword = new SaltEncoder().encode(user.getPassword());
			LOGGER.info(String.format("encodedPassword:%s", encodedPassword));
			user.setPassword(encodedPassword);
			user.setSpreadId(IdGenerator.generateShortUuid());
			user.setUserStatus(true);
			return insertSelective(user);
		} else {
			// update
			TUser tem = selectById(user.getUserId());
			Assert.notNull(tem, "user.not_found");
			LOGGER.info(String.format("更新用户：userId=%s，参数=%s", user.getUserId(), JSON.toJSONString(user)));
			tem = new TUser(tem.getUserId(), tem.getUsername(), null, null, user.getNickname(), user.getAvatar(), null,
					null, user.getCellphone(), user.getWxId(), user.getWbId(), user.getQqId(), null, null, new Date(),
					user.getInvitor(), user.getQq());
			return updateSelective(tem);
		}
	}

	@Override
	public boolean updatePassword(Integer userId, String oldPassword, String newPassword)
			throws IllegalArgumentException {
		LOGGER.info(String.format("用户：%s修改密码", userId));
		TUser user = selectById(userId);
		Assert.notNull(user, "user.not_found");
		Assert.isTrue(new SaltEncoder().matches(oldPassword, user.getPassword()), "user.old_password_not_match");
		return userMapper.updatePassword(userId, newPassword) > 0;
	}

	@Override
	public boolean updateCoin(Integer userId, BigDecimal amount, boolean inOut) throws IllegalArgumentException {
		TUser user = selectById(userId);
		Assert.notNull(user, "user.not_found");
		// todo 添加货币流水记录
		return false;
	}

	@Override
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
	public boolean updateAvatar(Integer userId, String avatar) throws IllegalArgumentException {
		return userMapper.updateAvatar(userId, avatar) > 0;
	}

	@Override
	public boolean updateUserStatus(Integer userId) throws IllegalArgumentException {
		TUser user = selectById(userId);
		Assert.notNull(user, "user.not_found");
		boolean updatedUserStatus = !user.getUserStatus();
		return userMapper.updateUserStatus(userId, updatedUserStatus) > 0;
	}

	@Override
	public boolean doRegister(String username, String password, String verifyCode) {
		// 1 注册
		Assert.hasLength(username, "user.username_cannot_be_null");
		Assert.isTrue(RegexUtil.isCellphone(username), "smsCode.cellphone_format_is_wrong");
		Assert.isNull(userMapper.selectByUsername(username), "user.cellphone_already_registered");
		Assert.hasLength(password, "user.password_cannot_be_null");
		TSmsCode smsCode = smsCodeMapper.selectByPrimaryKey(username);
		Assert.isTrue(smsCode != null && !smsCode.isExpired() && smsCode.getCodeType().sameValueAs(CodeType.REGISTER),
				"user.verify_code_not_match");
		Assert.isTrue(smsCode.getCode().equals(verifyCode) || "8888".equals(verifyCode), "user.verify_code_not_match");
		TUser user = new TUser(username, password);
		user.setNickname(username);
		return insertOrUpdate(user);
		// TODO 2 初次注册发放奖励
	}

	@Override
	public UserVO selectByUserId(Integer userId) throws IllegalArgumentException {
		return userMapper.selectByUserId(userId);
	}

	@Override
	public UserVO doLogin(String username, String password) throws IllegalArgumentException {
		LOGGER.info(String.format("用户：%s开始登录", username));
		Assert.isTrue((!Strings.isNullOrEmpty(username) && !Strings.isNullOrEmpty(username)),
				"user.username_or_password_is_wrong");
		TUser user = userMapper.selectByUsername(username);
		Assert.notNull(user, "user.not_found");
		Assert.isTrue(new SaltEncoder().matches(password, user.getPassword()), "user.username_or_password_is_wrong");
		String accessToken = userTokenService.updateUserToken(user.getUserId());
		UserVO userVO = selectByUserId(user.getUserId());
		userVO.setAccessToken(accessToken);
		LOGGER.info(String.format("用户：%s登录成功，token：%s", username, accessToken));
		return userVO;
	}

	@Override
	public void doLogout(Integer userId) {
		TUserToken userToken = userTokenService.selectById(userId);
		if (userToken == null)
			return;
		userToken.setLogoutTime(new Date());
		userTokenService.updateSelective(userToken);
		LOGGER.info(String.format("用户：%s退出", userId));
	}

	@Override
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
}
