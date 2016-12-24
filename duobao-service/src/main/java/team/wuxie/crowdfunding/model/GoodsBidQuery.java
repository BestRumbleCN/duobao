package team.wuxie.crowdfunding.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * @author WuGang
 * @since 1.0
 */
public class GoodsBidQuery implements Serializable {

    private static final long serialVersionUID = -8063566451304615979L;

    private Integer bidId;
    private String goodsName;
    private String winnerName;
    private String luckyNum;
    private Integer bidStatus;

    public Integer getBidId() {
        return bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getLuckyNum() {
        return luckyNum;
    }

    public void setLuckyNum(String luckyNum) {
        this.luckyNum = luckyNum;
    }

    public Integer getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(Integer bidStatus) {
        this.bidStatus = bidStatus;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("bidId", bidId)
                .add("goodsName", goodsName)
                .add("winnerName", winnerName)
                .add("luckyNum", luckyNum)
                .add("bidStatus", bidStatus)
                .toString();
    }
}
