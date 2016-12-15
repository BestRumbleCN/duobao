package team.wuxie.crowdfunding.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * @author WuGang
 * @since 1.0
 */
public class ActivityQuery implements Serializable {

    private static final long serialVersionUID = -6496004113601733593L;

    private Integer categoryId;
    private String name;
    private Integer enabled;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("categoryId", categoryId)
                .add("name", name)
                .add("enabled", enabled)
                .toString();
    }
}
