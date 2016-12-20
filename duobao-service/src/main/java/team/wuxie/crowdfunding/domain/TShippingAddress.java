package team.wuxie.crowdfunding.domain;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 收货地址表
 */
@Table(name = "t_shipping_address")
public class TShippingAddress implements Serializable {

    private static final long serialVersionUID = -6800768012936749408L;

    @Id
    @Column(name = "address_id")
    private Integer addressId;

    /**
     * 收件人姓名
     */
    private String name;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 是否默认地址
     */
    @Column(name = "is_default")
    private Boolean isDefault;

    /**
     * 收件人手机号
     */
    private String cellphone;

    /**
     * 地区信息
     */
    @Column(name = "base_address")
    private String baseAddress;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 省ID
     */
    @Column(name = "province_id")
    private Long provinceId;

    /**
     * 市ID
     */
    @Column(name = "city_id")
    private Long cityId;

    /**
     * 区县ID
     */
    @Column(name = "prefecture_id")
    private Long prefectureId;

    /**
     * 街道ID
     */
    @Column(name = "street_id")
    private Long streetId;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新日期
     */
    @Column(name = "update_time")
    private Date updateTime;


    public TShippingAddress(Integer addressId, String name, Integer userId, Boolean isDefault, String cellphone, String baseAddress, String address, Long provinceId, Long cityId, Long prefectureId, Long streetId, Date createTime, Date updateTime) {
        this.addressId = addressId;
        this.name = name;
        this.userId = userId;
        this.isDefault = isDefault;
        this.cellphone = cellphone;
        this.baseAddress = baseAddress;
        this.address = address;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.prefectureId = prefectureId;
        this.streetId = streetId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TShippingAddress() {
        super();
    }

    /**
     * @return address_id
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * @param addressId
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * 获取收件人姓名
     *
     * @return name - 收件人姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置收件人姓名
     *
     * @param name 收件人姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取是否默认地址
     *
     * @return is_default - 是否默认地址
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否默认地址
     *
     * @param isDefault 是否默认地址
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 获取收件人手机号
     *
     * @return cellphone - 收件人手机号
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * 设置收件人手机号
     *
     * @param cellphone 收件人手机号
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    /**
     * 获取地区信息
     *
     * @return base_address - 地区信息
     */
    public String getBaseAddress() {
        return baseAddress;
    }

    /**
     * 设置地区信息
     *
     * @param baseAddress 地区信息
     */
    public void setBaseAddress(String baseAddress) {
        this.baseAddress = baseAddress == null ? null : baseAddress.trim();
    }

    /**
     * 获取详细地址
     *
     * @return address - 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细地址
     *
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取省ID
     *
     * @return province_id - 省ID
     */
    public Long getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省ID
     *
     * @param provinceId 省ID
     */
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取市ID
     *
     * @return city_id - 市ID
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * 设置市ID
     *
     * @param cityId 市ID
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取区县ID
     *
     * @return prefecture_id - 区县ID
     */
    public Long getPrefectureId() {
        return prefectureId;
    }

    /**
     * 设置区县ID
     *
     * @param prefectureId 区县ID
     */
    public void setPrefectureId(Long prefectureId) {
        this.prefectureId = prefectureId;
    }

    /**
     * 获取街道ID
     *
     * @return street_id - 街道ID
     */
    public Long getStreetId() {
        return streetId;
    }

    /**
     * 设置街道ID
     *
     * @param streetId 街道ID
     */
    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    /**
     * 获取创建日期
     *
     * @return create_time - 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建日期
     *
     * @param createTime 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新日期
     *
     * @return update_time - 更新日期
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新日期
     *
     * @param updateTime 更新日期
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("addressId", addressId)
                .add("name", name)
                .add("userId", userId)
                .add("isDefault", isDefault)
                .add("cellphone", cellphone)
                .add("baseAddress", baseAddress)
                .add("address", address)
                .add("provinceId", provinceId)
                .add("cityId", cityId)
                .add("prefectureId", prefectureId)
                .add("streetId", streetId)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .toString();
    }
}