package team.wuxie.crowdfunding.vo;

import java.math.BigDecimal;
import java.util.Date;

import team.wuxie.crowdfunding.domain.enums.PocketStatus;

/**
 * ClassName:RedPocketVo <br/>
 * @author   fly
 * @version  1.0
 * @since    2016年11月23日 上午9:54:29
 * @see 	 
 */
public class RedPocketVo extends VO {
	
	private static final long serialVersionUID = -8769883352259226410L;

	/**
     * 红包ID
     */
    private Integer pocketId;

    /**
     * 红包名称
     */
    private String pocketName;


    /**
     * 满减金额（减）
     */
    private BigDecimal rebate;

    /**
     * 满减金额（满）
     */
    private BigDecimal fullMoney;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 红包状态：-1-过期、0-可使用、1-已使用
     */
    private PocketStatus pocketStatus;

    /**
     * 生效时间
     */
    private Date startDate;

    /**
     * 过期时间
     */
    private Date endDate;

	public Integer getPocketId() {
		return pocketId;
	}

	public String getPocketName() {
		return pocketName;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public BigDecimal getFullMoney() {
		return fullMoney;
	}

	public Integer getUserId() {
		return userId;
	}

	public PocketStatus getPocketStatus() {
		return pocketStatus;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setPocketId(Integer pocketId) {
		this.pocketId = pocketId;
	}

	public void setPocketName(String pocketName) {
		this.pocketName = pocketName;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}

	public void setFullMoney(BigDecimal fullMoney) {
		this.fullMoney = fullMoney;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setPocketStatus(PocketStatus pocketStatus) {
		this.pocketStatus = pocketStatus;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}

