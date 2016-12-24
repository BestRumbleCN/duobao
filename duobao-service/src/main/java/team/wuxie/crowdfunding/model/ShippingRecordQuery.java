package team.wuxie.crowdfunding.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * @author WuGang
 * @since 1.0
 */
public class ShippingRecordQuery implements Serializable {

    private static final long serialVersionUID = 6047482046709364682L;

    private Integer luckyNum;
    private String cellphone;
    private Integer shippingStatus;

    public Integer getLuckyNum() {
        return luckyNum;
    }

    public void setLuckyNum(Integer luckyNum) {
        this.luckyNum = luckyNum;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Integer getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("luckyNum", luckyNum)
                .add("cellphone", cellphone)
                .add("shippingStatus", shippingStatus)
                .toString();
    }
}
