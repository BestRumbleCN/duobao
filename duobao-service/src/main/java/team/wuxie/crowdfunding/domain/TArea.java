package team.wuxie.crowdfunding.domain;

import javax.persistence.*;

@Table(name = "t_area")
public class TArea {
    @Id
    @Column(name = "area_id")
    private Long areaId;

    /**
     * 父ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 省名称
     */
    private String province;

    /**
     * 市名称
     */
    private String city;

    /**
     * 县.区名称
     */
    private String district;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    public TArea(Long areaId, Long parentId, String name, String province, String city, String district, String longitude, String latitude) {
        this.areaId = areaId;
        this.parentId = parentId;
        this.name = name;
        this.province = province;
        this.city = city;
        this.district = district;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public TArea() {
        super();
    }

    /**
     * @return area_id
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * @param areaId
     */
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取父ID
     *
     * @return parent_id - 父ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父ID
     *
     * @param parentId 父ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取省名称
     *
     * @return province - 省名称
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省名称
     *
     * @param province 省名称
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 获取市名称
     *
     * @return city - 市名称
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市名称
     *
     * @param city 市名称
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 获取县.区名称
     *
     * @return district - 县.区名称
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置县.区名称
     *
     * @param district 县.区名称
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    /**
     * 获取纬度
     *
     * @return latitude - 纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }
}