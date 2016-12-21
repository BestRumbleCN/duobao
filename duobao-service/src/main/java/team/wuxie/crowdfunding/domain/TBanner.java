package team.wuxie.crowdfunding.domain;

import com.google.common.base.MoreObjects;
import team.wuxie.crowdfunding.domain.enums.BannerType;
import team.wuxie.crowdfunding.util.mybatis.typehandler.BannerTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_banner")
public class TBanner implements Serializable {

    private static final long serialVersionUID = 8532894289079883817L;

    public static final String PROP_BANNER_ID = "bannerId";
    public static final String PROP_BANNER_TYPE = "bannerType";
    public static final String PROP_IMG = "img";
    public static final String PROP_CONTENT = "content";
    public static final String PROP_STATUS = "status";

    @Id
    @Column(name = "banner_id")
    private Integer bannerId;

    /**
     * 1.url 2.商品内容
     */
    @Column(name = "banner_type")
    @ColumnType(typeHandler = BannerTypeHandler.class)
    private BannerType bannerType;

    /**
     * 图片
     */
    private String img;

    /**
     * 内容（1.url 2.goodsId）
     */
    private String content;

    /**
     * 是否开启
     */
    private Boolean status;

    public TBanner(Integer bannerId, BannerType bannerType, String img, String content, Boolean status) {
        this.bannerId = bannerId;
        this.bannerType = bannerType;
        this.img = img;
        this.content = content;
        this.status = status;
    }

    public TBanner() {
        super();
    }

    public TBanner newBanner(String img) {
        setStatus(Boolean.TRUE);
        setImg(img);
        return this;
    }

    public TBanner updateBanner(Integer bannerId) {
        setBannerId(bannerId);
        return this;
    }

    public TBanner changeStatus() {
        setStatus(!this.getStatus());
        return this;
    }

    /**
     * @return banner_id
     */
    public Integer getBannerId() {
        return bannerId;
    }

    /**
     * @param bannerId
     */
    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    /**
     * 获取1.url 2.商品内容
     *
     * @return banner_type - 1.url 2.商品内容
     */
    public BannerType getBannerType() {
        return bannerType;
    }

    /**
     * 设置1.url 2.商品内容
     *
     * @param bannerType 1.url 2.商品内容
     */
    public void setBannerType(BannerType bannerType) {
        this.bannerType = bannerType;
    }

    /**
     * 获取图片
     *
     * @return img - 图片
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置图片
     *
     * @param img 图片
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    /**
     * 获取内容（1.url 2.goodsId）
     *
     * @return content - 内容（1.url 2.goodsId）
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容（1.url 2.goodsId）
     *
     * @param content 内容（1.url 2.goodsId）
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取是否开启
     *
     * @return on - 是否开启
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置是否开启
     *
     * @param status:on 是否开启
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("bannerId", bannerId)
                .add("bannerType", bannerType)
                .add("img", img)
                .add("content", content)
                .add("status", status)
                .toString();
    }
}