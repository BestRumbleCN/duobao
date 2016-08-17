package team.wuxie.crowdfunding.domain;

import com.alibaba.fastjson.JSON;
import team.wuxie.crowdfunding.util.date.DateUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户认证表
 * </p>
 *
 * @author wushige
 * @date 2016-08-06 18:06
 */
@SuppressWarnings("unused")
@Table(name = "t_user_token")
public class TUserToken implements Serializable {

    //session过期时间(30天)
    public static final long SESSION_EXPIRED_TIME = 2592000000l;

    /**
     * 用户ID
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户TOKEN
     */
    @Column(name = "user_token")
    private String userToken;

    /**
     * 最后活跃时间
     */
    @Column(name = "active_time")
    private Date activeTime;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 登出时间
     */
    @Column(name = "logout_time")
    private Date logoutTime;

    /**
     * 在线时长
     */
    @Column(name = "online_time")
    private Long onlineTime;

    /**
     * 总登录次数
     */
    @Column(name = "total_login_num")
    private Integer totalLoginNum;

    /**
     * 当天登录次数
     */
    @Column(name = "day_login_num")
    private Integer dayLoginNum;

    /**
     * 会话状态 1正常 2正常登出 3强制登出
     */
    @Column(name = "session_status")
    private Integer sessionStatus;

    /**
     * 创建时只记录一次，不做其他修改
     */
    @Column(name = "create_time")
    private Date createTime;

    public TUserToken(Integer userId, String userToken, Date activeTime, Date loginTime, Date logoutTime, Long onlineTime, Integer totalLoginNum, Integer dayLoginNum, Integer sessionStatus, Date createTime) {
        this.userId = userId;
        this.userToken = userToken;
        this.activeTime = activeTime;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.onlineTime = onlineTime;
        this.totalLoginNum = totalLoginNum;
        this.dayLoginNum = dayLoginNum;
        this.sessionStatus = sessionStatus;
        this.createTime = createTime;
    }

    public TUserToken() {
    }

    /**
     * 新建userToken
     *
     * @param userId
     * @param userToken
     * @param activeTime
     * @param loginTime
     * @param logoutTime
     * @param onlineTime
     * @param totalLoginNum
     * @param dayLoginNum
     * @param sessionStatus
     * @param createTime
     * @return
     */
    public static TUserToken create(Integer userId, String userToken, Date activeTime, Date loginTime, Date logoutTime, Long onlineTime, Integer totalLoginNum, Integer dayLoginNum, Integer sessionStatus, Date createTime) {
        return new TUserToken(userId, userToken, activeTime, loginTime, logoutTime, onlineTime, totalLoginNum, dayLoginNum, sessionStatus, createTime);
    }

    /**
     * 更改userToken
     *
     * @param userToken
     * @param now
     * @return
     */
    public TUserToken changeUserToken(String userToken, Date now) {
        if (now == null) now = new Date();

        setTotalLoginNum(getTotalLoginNum() == null ? 0 : getTotalLoginNum() + 1);
        setDayLoginNum(DateUtils.isSameDay(now, getLoginTime()) ? (getDayLoginNum() == null ? 0 : getDayLoginNum() + 1) : 1);
        setUserToken(userToken);
        setActiveTime(now);
        setLoginTime(now);
        setLogoutTime(null);
        Integer sessionStatus = getSessionStatus();

        if (sessionStatus == SessionStatus.NORMAL.getValue())
            setOnlineTime(getOnlineTime() + DateUtils.timespaceOfSeconds(getActiveTime(), now));

        setSessionStatus(SessionStatus.NORMAL.getValue());
        return this;
    }

    /**
     * Session是否过期
     *
     * @param expiredPeriod 过期时间
     * @return
     */
    public boolean isExpired(long expiredPeriod) {
        if (null != getLogoutTime()) return true;

        Date lastActiveTime = this.getActiveTime();
        Date now = new Date();
        if (lastActiveTime == null) lastActiveTime = this.getCreateTime();
        return now.getTime() - lastActiveTime.getTime() > expiredPeriod;
    }

    /**
     * 退出
     *
     * @param logoutTime 登出时间
     * @param isForce    是否强制登出
     * @return
     */
    public TUserToken logout(Date logoutTime, boolean isForce) {
        if (null == logoutTime) logoutTime = new Date();
        setLogoutTime(logoutTime);
        setSessionStatus(isForce ? SessionStatus.LOGOUT_FORCE.getValue() : SessionStatus.LOGOUT_NORMAL.getValue());
        setOnlineTime(getOnlineTime() + DateUtils.timespaceOfSeconds(getActiveTime(), logoutTime));
        return this;
    }

    /**
     * 退出
     *
     * @param isForce 是否强制登出
     */
    public TUserToken logout(boolean isForce) {
        logout(new Date(), isForce);
        return this;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户TOKEN
     *
     * @return user_token - 用户TOKEN
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * 设置用户TOKEN
     *
     * @param userToken 用户TOKEN
     */
    public void setUserToken(String userToken) {
        this.userToken = userToken == null ? null : userToken.trim();
    }

    /**
     * 获取最后活跃时间
     *
     * @return active_time - 最后活跃时间
     */
    public Date getActiveTime() {
        return activeTime;
    }

    /**
     * 设置最后活跃时间
     *
     * @param activeTime 最后活跃时间
     */
    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    /**
     * 获取登录时间
     *
     * @return login_time - 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取登出时间
     *
     * @return logout_time - 登出时间
     */
    public Date getLogoutTime() {
        return logoutTime;
    }

    /**
     * 设置登出时间
     *
     * @param logoutTime 登出时间
     */
    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    /**
     * 获取在线时长
     *
     * @return online_time - 在线时长
     */
    public Long getOnlineTime() {
        return onlineTime;
    }

    /**
     * 设置在线时长
     *
     * @param onlineTime 在线时长
     */
    public void setOnlineTime(Long onlineTime) {
        this.onlineTime = onlineTime;
    }

    /**
     * 获取总登录次数
     *
     * @return total_login_num - 总登录次数
     */
    public Integer getTotalLoginNum() {
        return totalLoginNum;
    }

    /**
     * 设置总登录次数
     *
     * @param totalLoginNum 总登录次数
     */
    public void setTotalLoginNum(Integer totalLoginNum) {
        this.totalLoginNum = totalLoginNum;
    }

    /**
     * 获取当天登录次数
     *
     * @return day_login_num - 当天登录次数
     */
    public Integer getDayLoginNum() {
        return dayLoginNum;
    }

    /**
     * 设置当天登录次数
     *
     * @param dayLoginNum 当天登录次数
     */
    public void setDayLoginNum(Integer dayLoginNum) {
        this.dayLoginNum = dayLoginNum;
    }

    /**
     * 获取会话状态 1正常 2正常登出 3强制登出
     *
     * @return session_status - 会话状态 1正常 2正常登出 3强制登出
     */
    public Integer getSessionStatus() {
        return sessionStatus;
    }

    /**
     * 设置会话状态 1正常 2正常登出 3强制登出
     *
     * @param sessionStatus 会话状态 1正常 2正常登出 3强制登出
     */
    public void setSessionStatus(Integer sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    /**
     * 获取创建时只记录一次，不做其他修改
     *
     * @return create_time - 创建时只记录一次，不做其他修改
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时只记录一次，不做其他修改
     *
     * @param createTime 创建时只记录一次，不做其他修改
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}