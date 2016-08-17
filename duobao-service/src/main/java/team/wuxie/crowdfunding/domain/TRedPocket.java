package team.wuxie.crowdfunding.domain;

import com.alibaba.fastjson.JSON;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 红包明细表
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:34
 */
@SuppressWarnings("unused")
@Table(name = "t_red_pocket")
public class TRedPocket implements Serializable {
    /**
     * 红包ID
     */
    @Id
    @Column(name = "pocket_id")
    private Integer pocketId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 红包名称
     */
    @Column(name = "pocket_name")
    private String pocketName;

    /**
     * 红包状态：1-可使用、0-过期已使用
     */
    @Column(name = "pocket_status")
    private Boolean pocketStatus;

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

    public TRedPocket(Integer pocketId, Integer userId, String pocketName, Boolean pocketStatus, Date createTime, Date updateTime) {
        this.pocketId = pocketId;
        this.userId = userId;
        this.pocketName = pocketName;
        this.pocketStatus = pocketStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TRedPocket() {
    }

    /**
     * 获取红包ID
     *
     * @return pocket_id - 红包ID
     */
    public Integer getPocketId() {
        return pocketId;
    }

    /**
     * 设置红包ID
     *
     * @param pocketId 红包ID
     */
    public void setPocketId(Integer pocketId) {
        this.pocketId = pocketId;
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
     * 获取红包名称
     *
     * @return pocket_name - 红包名称
     */
    public String getPocketName() {
        return pocketName;
    }

    /**
     * 设置红包名称
     *
     * @param pocketName 红包名称
     */
    public void setPocketName(String pocketName) {
        this.pocketName = pocketName == null ? null : pocketName.trim();
    }

    /**
     * 获取红包状态：1-可使用、0-过期已使用
     *
     * @return pocket_status - 红包状态：1-可使用、0-过期已使用
     */
    public Boolean getPocketStatus() {
        return pocketStatus;
    }

    /**
     * 设置红包状态：1-可使用、0-过期已使用
     *
     * @param pocketStatus 红包状态：1-可使用、0-过期已使用
     */
    public void setPocketStatus(Boolean pocketStatus) {
        this.pocketStatus = pocketStatus;
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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}