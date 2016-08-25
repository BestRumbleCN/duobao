package team.wuxie.crowdfunding.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * <p>
 * 用户VO
 * </p>
 *
 * @author wushige
 * @date 2016-08-11 10:35
 */
@ApiModel(value = "User", description = "用户详情")
public class UserVO extends VO {

    /**
     * accessToken
     */
    @ApiModelProperty(value = "用户token")
    private String accessToken;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Integer userId;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 推广ID
     */
    @ApiModelProperty(value = "推广ID")
    private String spreadId;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String avatar;

    /**
     * 抢币（虚拟货币）
     */
    @ApiModelProperty(value = "抢币")
    private BigDecimal coin;

    /**
     * 积分
     */
    @ApiModelProperty(value = "积分")
    private Integer integral;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String cellphone;

    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址")
    private String shippingAddress;

    public UserVO() {
    }

    public UserVO(String accessToken, Integer userId, String username, String spreadId, String nickname, String avatar, BigDecimal coin, Integer integral, String cellphone, String shippingAddress) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.username = username;
        this.spreadId = spreadId;
        this.nickname = nickname;
        this.avatar = avatar;
        this.coin = coin;
        this.integral = integral;
        this.cellphone = cellphone;
        this.shippingAddress = shippingAddress;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSpreadId() {
        return spreadId;
    }

    public void setSpreadId(String spreadId) {
        this.spreadId = spreadId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public BigDecimal getCoin() {
        return coin;
    }

    public void setCoin(BigDecimal coin) {
        this.coin = coin;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
