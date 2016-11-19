package team.wuxie.crowdfunding.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import team.wuxie.crowdfunding.domain.enums.TradeSource;
import team.wuxie.crowdfunding.domain.enums.TradeStatus;
import team.wuxie.crowdfunding.domain.enums.TradeType;
import team.wuxie.crowdfunding.util.mybatis.typehandler.TradeSourceTypeHandler;
import team.wuxie.crowdfunding.util.mybatis.typehandler.TradeStatusTypeHandler;
import team.wuxie.crowdfunding.util.mybatis.typehandler.TradeTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

@Table(name = "t_trade")
public class TTrade {
    @Id
    @Column(name = "trade_id")
    private Integer tradeId;
    
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "trade_no")
    private String tradeNo;

    /**
     * 1.支付宝 2.微信
     */
    @Column(name = "trade_source")
    @ColumnType(typeHandler = TradeSourceTypeHandler.class)
    private TradeSource tradeSource;

    /**
     * -2,支付失败 -1支付取消 0待支付 1支付成功
     */
    @Column(name = "trade_status")
    @ColumnType(typeHandler = TradeStatusTypeHandler.class)
    private TradeStatus tradeStatus;

    /**
     * 1.点券充值 2.购买商品
     */
    @Column(name = "trade_type")
    @ColumnType(typeHandler = TradeTypeHandler.class)
    private TradeType tradeType;

    /**
     * 订单关键字
     */
    private String keyword;

    /**
     * 订单信息
     */
    @Column(name = "trade_info")
    private String tradeInfo;

    /**
     * 订单描述
     */
    private String description;

    /**
     * 支付回调结果信息
     */
    @Column(name = "pay_msg")
    private String payMsg;

    /**
     * 交易金额
     */
    private String amount;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public TTrade(Integer tradeId,Integer userId, String tradeNo, TradeSource tradeSource, TradeStatus tradeStatus, TradeType tradeType, String keyword, String tradeInfo, String description, String payMsg, String amount, Date createTime, Date updateTime) {
        this.tradeId = tradeId;
        this.userId = userId;
        this.tradeNo = tradeNo;
        this.tradeSource = tradeSource;
        this.tradeStatus = tradeStatus;
        this.tradeType = tradeType;
        this.keyword = keyword;
        this.tradeInfo = tradeInfo;
        this.description = description;
        this.payMsg = payMsg;
        this.amount = amount;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TTrade() {
        super();
    }

    /**
     * @return trade_id
     */
    public Integer getTradeId() {
        return tradeId;
    }

    /**
     * @param tradeId
     */
    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
     * @return trade_no
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * @param tradeNo
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    /**
     * 获取1.支付宝 2.微信
     *
     * @return trade_source - 1.支付宝 2.微信
     */
    public TradeSource getTradeSource() {
        return tradeSource;
    }

    /**
     * 设置1.支付宝 2.微信
     *
     * @param tradeSource 1.支付宝 2.微信
     */
    public void setTradeSource(TradeSource tradeSource) {
        this.tradeSource = tradeSource;
    }

    /**
     * 获取-2,支付失败 -1支付取消 0待支付 1支付成功
     *
     * @return trade_status - -2,支付失败 -1支付取消 0待支付 1支付成功
     */
    public TradeStatus getTradeStatus() {
        return tradeStatus;
    }

    /**
     * 设置-2,支付失败 -1支付取消 0待支付 1支付成功
     *
     * @param tradeStatus -2,支付失败 -1支付取消 0待支付 1支付成功
     */
    public void setTradeStatus(TradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    /**
     * 获取1.点券充值 2.购买商品
     *
     * @return trade_type - 1.点券充值 2.购买商品
     */
    public TradeType getTradeType() {
        return tradeType;
    }

    /**
     * 设置1.点券充值 2.购买商品
     *
     * @param tradeType 1.点券充值 2.购买商品
     */
    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    /**
     * 获取订单关键字
     *
     * @return keyword - 订单关键字
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置订单关键字
     *
     * @param keyword 订单关键字
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    /**
     * 获取订单信息
     *
     * @return trade_info - 订单信息
     */
    public String getTradeInfo() {
        return tradeInfo;
    }

    /**
     * 设置订单信息
     *
     * @param tradeInfo 订单信息
     */
    public void setTradeInfo(String tradeInfo) {
        this.tradeInfo = tradeInfo == null ? null : tradeInfo.trim();
    }

    /**
     * 获取订单描述
     *
     * @return description - 订单描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置订单描述
     *
     * @param description 订单描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取支付回调结果信息
     *
     * @return pay_msg - 支付回调结果信息
     */
    public String getPayMsg() {
        return payMsg;
    }

    /**
     * 设置支付回调结果信息
     *
     * @param payMsg 支付回调结果信息
     */
    public void setPayMsg(String payMsg) {
        this.payMsg = payMsg == null ? null : payMsg.trim();
    }

    /**
     * 获取交易金额
     *
     * @return amount - 交易金额
     */
    public String getAmount() {
        return amount;
    }

    /**
     * 设置交易金额
     *
     * @param amount 交易金额
     */
    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}