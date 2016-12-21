package team.wuxie.crowdfunding.domain;

import com.google.common.base.MoreObjects;
import team.wuxie.crowdfunding.domain.enums.IntegralType;
import team.wuxie.crowdfunding.util.mybatis.typehandler.IntegralTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 积分明细表
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:34
 */
@SuppressWarnings("unused")
@Table(name = "t_integral")
public class TIntegral implements Serializable {

    private static final long serialVersionUID = 7403733861810364547L;

    /**
     * 积分明细ID
     */
    @Id
    @Column(name = "integral_id")
    private Integer integralId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 积分类型：0-签到、1-新手任务
     */
    @Column(name = "integral_type")
    @ColumnType(typeHandler = IntegralTypeHandler.class)
    private IntegralType integralType;

    /**
     * 积分来源或消耗：1-增加、0-消耗
     */
    @Column(name = "in_out")
    private Boolean inOut;

    /**
     * 单次积分值
     */
    private Integer amount;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    public TIntegral(Integer integralId, Integer userId, IntegralType integralType, Boolean inOut, Integer amount, Date createTime) {
        this.integralId = integralId;
        this.userId = userId;
        this.integralType = integralType;
        this.inOut = inOut;
        this.amount = amount;
        this.createTime = createTime;
    }

    public TIntegral(Integer userId, IntegralType integralType, Boolean inOut, Integer amount) {
        this.userId = userId;
        this.integralType = integralType;
        this.inOut = inOut;
        this.amount = amount;
    }

    public TIntegral() {
    }

    /**
     * 获取积分明细ID
     *
     * @return integral_id - 积分明细ID
     */
    public Integer getIntegralId() {
        return integralId;
    }

    /**
     * 设置积分明细ID
     *
     * @param integralId 积分明细ID
     */
    public void setIntegralId(Integer integralId) {
        this.integralId = integralId;
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
     * 获取积分类型：0-签到、1-新手任务
     *
     * @return integral_type - 积分类型：0-签到、1-新手任务
     */
    public IntegralType getIntegralType() {
        return integralType;
    }

    /**
     * 设置积分类型：0-签到、1-新手任务
     *
     * @param integralType 积分类型：0-签到、1-新手任务
     */
    public void setIntegralType(IntegralType integralType) {
        this.integralType = integralType;
    }

    /**
     * 获取积分来源或消耗：1-增加、0-消耗
     *
     * @return in_out - 积分来源或消耗：1-增加、0-消耗
     */
    public Boolean getInOut() {
        return inOut;
    }

    /**
     * 设置积分来源或消耗：1-增加、0-消耗
     *
     * @param inOut 积分来源或消耗：1-增加、0-消耗
     */
    public void setInOut(Boolean inOut) {
        this.inOut = inOut;
    }

    /**
     * 获取单次积分值
     *
     * @return amount - 单次积分值
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置单次积分值
     *
     * @param amount 单次积分值
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("integralId", integralId)
                .add("userId", userId)
                .add("integralType", integralType)
                .add("inOut", inOut)
                .add("amount", amount)
                .add("createTime", createTime)
                .toString();
    }
}