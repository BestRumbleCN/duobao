package team.wuxie.crowdfunding.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import team.wuxie.crowdfunding.util.mybatis.typehandler.RoleTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wushige
 * @date 2016-08-08 15:06
 */
@SuppressWarnings("unused")
@Table(name = "t_user")
public class TUser implements Serializable {
    /**
     * 用户ID
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JSONField(serialize = false)
    private String password;

    /**
     * 推广ID
     */
    @Column(name = "spread_id")
    private String spreadId;
    
	/**
     * 邀请者推广ID
     */
    private String invitor;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 抢币（虚拟货币）
     */
    private BigDecimal coin;

    /**
     * 积分
     */
    private Integer integral;

    /**
     * 手机号码
     */
    private String cellphone;

    /**
     * 微信 openId
     */
    private String wxId;

    /**
     * 微博 openId
     */
    private String wbId;

    /**
     * QQ openId
     */
    private String qqId;

    /**
     * 用户状态：1-正常、0-禁用
     */
    @Column(name = "user_status")
    private Boolean userStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
    
    /**
     * qq
     */
    private String qq;

    public TUser(Integer userId, String username, String password, String spreadId, String nickname, String avatar, BigDecimal coin, Integer integral, String cellphone, String wxId, String wbId, String qqId, Boolean userStatus, Date createTime, Date updateTime, String invitor, String qq) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.spreadId = spreadId;
        this.nickname = nickname;
        this.avatar = avatar;
        this.coin = coin;
        this.integral = integral;
        this.cellphone = cellphone;
        this.wxId = wxId;
        this.wbId = wbId;
        this.qqId = qqId;
        this.userStatus = userStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.invitor = invitor;
        this.qq = qq;
    }

    public TUser() {
    }

    public TUser(Integer userId, String nickname, String avatar, String qq) {
        this.userId = userId;
        this.nickname = nickname;
        this.avatar = avatar;
        this.qq = qq;
    }

    public TUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //public boolean isValid() {
    //    return this.getUserStatus();
    //}

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
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取推广ID
     *
     * @return spread_id - 推广ID
     */
    public String getSpreadId() {
        return spreadId;
    }

    /**
     * 设置推广ID
     *
     * @param spreadId 推广ID
     */
    public void setSpreadId(String spreadId) {
        this.spreadId = spreadId == null ? null : spreadId.trim();
    }
    
    public String getInvitor() {
 		return invitor;
 	}

 	public void setInvitor(String invitor) {
 		this.invitor = invitor;
 	}
 	
    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取用户头像
     *
     * @return avatar - 用户头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置用户头像
     *
     * @param avatar 用户头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * 获取抢币（虚拟货币）
     *
     * @return coin - 抢币（虚拟货币）
     */
    public BigDecimal getCoin() {
        return coin;
    }

    /**
     * 设置抢币（虚拟货币）
     *
     * @param coin 抢币（虚拟货币）
     */
    public void setCoin(BigDecimal coin) {
        this.coin = coin;
    }

    /**
     * 获取积分
     *
     * @return integral - 积分
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * 设置积分
     *
     * @param integral 积分
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * 获取手机号码
     *
     * @return cellphone - 手机号码
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * 设置手机号码
     *
     * @param cellphone 手机号码
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    /**
     * 获取微信openId
     *
     * @return wxId - 微信openId
     */
    public String getWxId() {
        return wxId;
    }

    /**
     * 设置微信openId
     *
     * @param wxId 微信openId
     */
    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    /**
     * 获取微博openId
     *
     * @return wbId - 微博openId
     */
    public String getWbId() {
        return wbId;
    }

    /**
     * 设置微博openId
     *
     * @param wbId 微博openId
     */
    public void setWbId(String wbId) {
        this.wbId = wbId;
    }

    /**
     * 获取QQ openId
     *
     * @return qqId - QQ openId
     */
    public String getQqId() {
        return qqId;
    }

    /**
     * 设置QQ openId
     *
     * @param qqId QQ openId
     */
    public void setQqId(String qqId) {
        this.qqId = qqId;
    }

    /**
     * 获取用户状态：1-正常、0-禁用
     *
     * @return user_status - 用户状态：1-正常、0-禁用
     */
    public Boolean getUserStatus() {
        return userStatus;
    }

    /**
     * 设置用户状态：1-正常、0-禁用
     *
     * @param userStatus 用户状态：1-正常、0-禁用
     */
    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}