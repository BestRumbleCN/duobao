package team.wuxie.crowdfunding.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * @author WuGang
 * @since 1.0
 */
public class ShippingRecordQuery implements Serializable {

    private static final long serialVersionUID = 6047482046709364682L;

    private Integer bidId;
    private String cellphone;
    private Integer shippingStatus;

    public Integer getBidId() {
        return bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
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
                .add("bidId", bidId)
                .add("cellphone", cellphone)
                .add("shippingStatus", shippingStatus)
                .toString();
    }
}
