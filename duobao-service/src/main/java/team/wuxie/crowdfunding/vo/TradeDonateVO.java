package team.wuxie.crowdfunding.vo;

import java.util.Date;

public class TradeDonateVO extends VO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nickname;
	private String amount;
	private Date tradeTime;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
}
