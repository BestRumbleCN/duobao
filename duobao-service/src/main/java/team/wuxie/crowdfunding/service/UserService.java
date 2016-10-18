package team.wuxie.crowdfunding.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import team.wuxie.crowdfunding.domain.IntegralType;
import team.wuxie.crowdfunding.domain.TUser;
import team.wuxie.crowdfunding.exception.ServiceException;
import team.wuxie.crowdfunding.util.service.BaseService;
import team.wuxie.crowdfunding.vo.UserVO;

/**
 * <p>
 * 用户Service
 * </p>
 *
 * @author wushige
 * @date 2016-07-25 16:23
 */
public interface UserService extends BaseService<TUser> {

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    TUser selectByUsername(String username);

    /**
     * 模糊查询用户
     *
     * @param map
     * @return
     */
    List<TUser> selectAllLike(Map<String, String> map);

    /**
     * 添加或者更新用户
     *
     * @param user
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    boolean insertOrUpdate(TUser user) throws IllegalArgumentException;

    /**
     * 更新用户密码
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     * @throws ServiceException
     */
    @Transactional
    boolean updatePassword(Integer userId, String oldPassword, String newPassword) throws IllegalArgumentException;

    /**
     * 更新用户抢币（虚拟货币）
     *
     * @param userId
     * @param amount
     * @param inOut      true-增加、false-减少
     * @return
     * @throws ServiceException
     */
    @Transactional
    boolean updateCoin(Integer userId, BigDecimal amount, boolean inOut) throws IllegalArgumentException;

    /**
     * 更新用户积分
     *
     * @param userId
     * @param amount
     * @param integralType
     * @param inOut      true-增加、false-减少
     * @return
     * @throws ServiceException
     */
    @Transactional
    boolean updateIntegral(Integer userId, Integer amount, IntegralType integralType, boolean inOut) throws IllegalArgumentException;

    /**
     * 更新用户头像
     *
     * @param userId
     * @param avatar
     * @return
     * @throws ServiceException
     */
    @Transactional
    boolean updateAvatar(Integer userId, String avatar) throws IllegalArgumentException;

    /**
     * 更新用户状态
     *
     * @param userId
     * @return
     */
    @Transactional
    boolean updateUserStatus(Integer userId) throws IllegalArgumentException;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param verifyCode
     * @return
     */
    @Transactional
    boolean doRegister(String username, String password, String verifyCode);

    /**
     * 获取UserVo
     *
     * @param userId
     * @return
     */
    UserVO selectByUserId(Integer userId) throws IllegalArgumentException;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    UserVO doLogin(String username, String password) throws IllegalArgumentException;

    /**
     * 用户退出
     *
     * @param userId
     * @return
     */
    @Transactional
    void doLogout(Integer userId);
    
    /**
     * 添加邀请码
     * @author fly
     * @param userId
     * @param invitor
     * @return
     * @throws IllegalArgumentException  
     * @since
     */
    @Transactional
    UserVO addInvitor(Integer userId, String invitor)throws IllegalArgumentException;
}
