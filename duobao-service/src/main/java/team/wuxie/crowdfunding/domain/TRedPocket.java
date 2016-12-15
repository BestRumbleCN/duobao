package team.wuxie.crowdfunding.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import team.wuxie.crowdfunding.domain.enums.PocketSource;
import team.wuxie.crowdfunding.domain.enums.PocketStatus;
import team.wuxie.crowdfunding.util.mybatis.typehandler.PocketSourceTypeHandler;
import team.wuxie.crowdfunding.util.mybatis.typehandler.PocketStatusTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

@Table(name = "t_red_pocket")
public class TRedPocket {
    /**
     * 红包ID
     */
    @Id
    @Column(name = "pocket_id")
    private Integer pocketId;

    /**
     * 红包名称
     */
    @Column(name = "pocket_name")
    private String pocketName;

    /**
     * 来源ID
     */
    @Column(name = "source_id")
    private Integer sourceId;

    /**
     * 红包来源：1后台发放 2活动抽取
     */
    @Column(name = "pocket_source")
    @ColumnType(typeHandler = PocketSourceTypeHandler.class)
    private PocketSource pocketSource;

    /**
     * 满减金额（减）
     */
    private BigDecimal rebate;

    /**
     * 满减金额（满）
     */
    @Column(name = "full_money")
    private BigDecimal fullMoney;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 红包状态：-1-过期、0-可使用、1-已使用
     */
    @Column(name = "pocket_status")
    @ColumnType(typeHandler = PocketStatusTypeHandler.class)
    private PocketStatus pocketStatus;

    /**
     * 生效时间
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 过期时间
     */
    @Column(name = "end_date")
    private Date endDate;

    public TRedPocket(Integer pocketId, String pocketName, Integer sourceId, PocketSource pocketSource, BigDecimal rebate, BigDecimal fullMoney, Integer userId, PocketStatus pocketStatus, Date startDate, Date endDate) {
        this.pocketId = pocketId;
        this.pocketName = pocketName;
        this.sourceId = sourceId;
        this.pocketSource = pocketSource;
        this.rebate = rebate;
        this.fullMoney = fullMoney;
        this.userId = userId;
        this.pocketStatus = pocketStatus;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TRedPocket() {
        super();
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
     * 获取来源ID
     *
     * @return source_id - 来源ID
     */
    public Integer getSourceId() {
        return sourceId;
    }

    /**
     * 设置来源ID
     *
     * @param sourceId 来源ID
     */
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * 获取红包来源：1后台发放 2活动抽取
     *
     * @return pocket_source - 红包来源：1后台发放 2活动抽取
     */
    public PocketSource getPocketSource() {
        return pocketSource;
    }

    /**
     * 设置红包来源：1后台发放 2活动抽取
     *
     * @param pocketSource 红包来源：1后台发放 2活动抽取
     */
    public void setPocketSource(PocketSource pocketSource) {
        this.pocketSource = pocketSource;
    }

    /**
     * 获取满减金额（减）
     *
     * @return rebate - 满减金额（减）
     */
    public BigDecimal getRebate() {
        return rebate;
    }

    /**
     * 设置满减金额（减）
     *
     * @param rebate 满减金额（减）
     */
    public void setRebate(BigDecimal rebate) {
        this.rebate = rebate;
    }

    /**
     * 获取满减金额（满）
     *
     * @return full_money - 满减金额（满）
     */
    public BigDecimal getFullMoney() {
        return fullMoney;
    }

    /**
     * 设置满减金额（满）
     *
     * @param fullMoney 满减金额（满）
     */
    public void setFullMoney(BigDecimal fullMoney) {
        this.fullMoney = fullMoney;
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
     * 获取红包状态：1-可使用、0-过期已使用
     *
     * @return pocket_status - 红包状态：1-可使用、0-过期已使用
     */
    public PocketStatus getPocketStatus() {
        return pocketStatus;
    }

    /**
     * 设置红包状态：1-可使用、0-过期已使用
     *
     * @param pocketStatus 红包状态：1-可使用、0-过期已使用
     */
    public void setPocketStatus(PocketStatus pocketStatus) {
        this.pocketStatus = pocketStatus;
    }

    /**
     * 获取生效时间
     *
     * @return start_date - 生效时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置生效时间
     *
     * @param startDate 生效时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取过期时间
     *
     * @return end_date - 过期时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置过期时间
     *
     * @param endDate 过期时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}