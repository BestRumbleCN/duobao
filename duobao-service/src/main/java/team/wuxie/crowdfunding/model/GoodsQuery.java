package team.wuxie.crowdfunding.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * @author WuGang
 * @since 1.0
 */
public class GoodsQuery implements Serializable {

    private static final long serialVersionUID = 2773489058968017960L;

    private String goodsName;
    private Integer goodsStatus;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("goodsName", goodsName)
                .add("goodsStatus", goodsStatus)
                .toString();
    }
}
