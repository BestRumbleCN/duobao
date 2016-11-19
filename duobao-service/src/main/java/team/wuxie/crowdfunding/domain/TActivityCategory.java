package team.wuxie.crowdfunding.domain;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 活动分类表
 */
@Table(name = "t_activity_category")
public class TActivityCategory implements Serializable {

    private static final long serialVersionUID = 3885860586272058598L;

    /**
     * 活动分类ID
     */
    @Id
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类图片
     */
    private String img;

    /**
     * 分类状态：1-上架、0-下架
     */
    private Boolean enabled;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    public TActivityCategory(Integer categoryId, String name, String img, Boolean enabled, Date createTime, Date updateTime) {
        this.categoryId = categoryId;
        this.name = name;
        this.img = img;
        this.enabled = enabled;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TActivityCategory() {
        super();
    }

    /**
     * 获取活动分类ID
     *
     * @return category_id - 活动分类ID
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置活动分类ID
     *
     * @param categoryId 活动分类ID
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取分类名称
     *
     * @return name - 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称
     *
     * @param name 分类名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取分类图片
     *
     * @return img - 分类图片
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置分类图片
     *
     * @param img 分类图片
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    /**
     * 获取分类状态：1-上架、0-下架
     *
     * @return enabled - 分类状态：1-上架、0-下架
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置分类状态：1-上架、0-下架
     *
     * @param enabled 分类状态：1-上架、0-下架
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("categoryId", categoryId)
                .add("name", name)
                .add("img", img)
                .add("enabled", enabled)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .toString();
    }
}