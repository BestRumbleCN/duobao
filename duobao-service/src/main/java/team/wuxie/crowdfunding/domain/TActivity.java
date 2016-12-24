package team.wuxie.crowdfunding.domain;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 活动表
 */
@Table(name = "t_activity")
public class TActivity implements Serializable {

    private static final long serialVersionUID = 4968248225866018359L;

    public static final String PROP_ACTIVITY_ID = "activityId";
    public static final String PROP_CATEGORY_ID = "categoryId";
    public static final String PROP_NAME = "name";
    public static final String PROP_CONTENT = "content";
    public static final String PROP_ENABLED = "enabled";
    public static final String PROP_CREATE_TIME = "create_time";
    public static final String PROP_UPDATE_TIME = "update_time";

    /**
     * 活动ID
     */
    @Id
    @Column(name = "activity_id")
    private Integer activityId;

    /**
     * 活动分类ID
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动内容
     */
    private String content;

    /**
     * 活动状态：1-上架、0-下架
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

    @Transient
    private String categoryName;

    public TActivity(Integer activityId, Integer categoryId, String name, String content, Boolean enabled, Date createTime, Date updateTime) {
        this.activityId = activityId;
        this.categoryId = categoryId;
        this.name = name;
        this.content = content;
        this.enabled = enabled;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TActivity() {
        super();
    }

    public TActivity newActivity() {
        setEnabled(Boolean.TRUE);
        setCreateTime(new Date());
        setUpdateTime(new Date());
        return this;
    }

    public TActivity updateActivity(Integer activityId) {
        setActivityId(activityId);
        setUpdateTime(new Date());
        return this;
    }

    public TActivity changeStatus() {
        setEnabled(!this.getEnabled());
        return this;
    }

    /**
     * 获取活动ID
     *
     * @return activity_id - 活动ID
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * 设置活动ID
     *
     * @param activityId 活动ID
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
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
     * 获取活动名称
     *
     * @return name - 活动名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置活动名称
     *
     * @param name 活动名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取活动内容
     *
     * @return content - 活动内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置活动内容
     *
     * @param content 活动内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取活动状态：1-上架、0-下架
     *
     * @return enabled - 活动状态：1-上架、0-下架
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置活动状态：1-上架、0-下架
     *
     * @param enabled 活动状态：1-上架、0-下架
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("activityId", activityId)
                .add("categoryId", categoryId)
                .add("name", name)
                .add("content", content)
                .add("enabled", enabled)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .toString();
    }
}