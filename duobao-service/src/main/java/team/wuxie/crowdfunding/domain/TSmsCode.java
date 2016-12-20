package team.wuxie.crowdfunding.domain;

import com.google.common.base.MoreObjects;
import team.wuxie.crowdfunding.domain.enums.CodeType;
import team.wuxie.crowdfunding.util.mybatis.typehandler.CodeTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 短信验证码表
 * </p>
 *
 * @author wushige
 * @date 2016-08-18 17:34
 */
@SuppressWarnings("unused")
@Table(name = "t_sms_code")
public class TSmsCode implements Serializable {

    private static final long serialVersionUID = 3952200779827236836L;

    /**
     * 手机号
     */
    @Id
    private String cellphone;

    /**
     * 验证码
     */
    private String code;

    /**
     * 最近一次发送接收验证码的时间
     */
    @Column(name = "receive_time")
    private Date receiveTime;

    /**
     * 每日发送验证码次数
     */
    private Integer times;

    /**
     * 是否验证：1-已验证、0-未验证
     */
    private Boolean verified;

    /**
     * 验证码类型
     */
    @Column(name = "code_type")
    @ColumnType(typeHandler = CodeTypeHandler.class)
    private CodeType codeType;

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

    public TSmsCode(String cellphone, String code, Date receiveTime, Integer times, Boolean verified, CodeType codeType, Date createTime, Date updateTime) {
        this.cellphone = cellphone;
        this.code = code;
        this.receiveTime = receiveTime;
        this.times = times;
        this.verified = verified;
        this.codeType = codeType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TSmsCode(String cellphone, String code, Date receiveTime, Integer times, Boolean verified, CodeType codeType) {
        this.cellphone = cellphone;
        this.code = code;
        this.receiveTime = receiveTime;
        this.times = times;
        this.verified = verified;
        this.codeType = codeType;
    }

    public TSmsCode(String cellphone) {
        this.cellphone = cellphone;
    }

    public TSmsCode() {
    }

    /**
     * 手机验证码过期
     *
     * @return
     */
    public boolean isExpired() {
        //短信验证码过期时间：15分钟 = 15 * 60 * 1000
        final long EXPIRED_TIME = 900000;
        return new Date().getTime() - receiveTime.getTime() > EXPIRED_TIME;
    }

    /**
     * 检查请求验证码的时间间隔是否合法
     *
     * @return
     */
    public boolean isOften() {
        //短信验证码间隔时间：1分钟 = 60 * 1000
        final long INTERVAL_TIME = 60000;
        return new Date().getTime() - receiveTime.getTime() > INTERVAL_TIME;
    }

    /**
     * 判断二维码是否有效
     *
     * @param type
     * @param smsCode
     * @return
     */
    public boolean isLegal(CodeType type, String smsCode) {
        return !(verified || !type.sameValueAs(codeType) || !smsCode.equals(code));
    }

    /**
     * 获取手机号
     *
     * @return cellphone - 手机号
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * 设置手机号
     *
     * @param cellphone 手机号
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    /**
     * 获取验证码
     *
     * @return code - 验证码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置验证码
     *
     * @param code 验证码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取最近一次发送接收验证码的时间
     *
     * @return receive_time - 最近一次发送接收验证码的时间
     */
    public Date getReceiveTime() {
        return receiveTime;
    }

    /**
     * 设置最近一次发送接收验证码的时间
     *
     * @param receiveTime 最近一次发送接收验证码的时间
     */
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * 获取每日发送验证码次数
     *
     * @return times - 每日发送验证码次数
     */
    public Integer getTimes() {
        return times;
    }

    /**
     * 设置每日发送验证码次数
     *
     * @param times 每日发送验证码次数
     */
    public void setTimes(Integer times) {
        this.times = times;
    }

    /**
     * 获取是否验证：1-已验证、0-未验证
     *
     * @return verified - 是否验证：1-已验证、0-未验证
     */
    public Boolean getVerified() {
        return verified;
    }

    /**
     * 设置是否验证：1-已验证、0-未验证
     *
     * @param verified 是否验证：1-已验证、0-未验证
     */
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    /**
     * 获取验证码类型
     *
     * @return code_type - 验证码类型
     */
    public CodeType getCodeType() {
        return codeType;
    }

    /**
     * 设置验证码类型
     *
     * @param codeType 验证码类型
     */
    public void setCodeType(CodeType codeType) {
        this.codeType = codeType;
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
                .add("cellphone", cellphone)
                .add("code", code)
                .add("receiveTime", receiveTime)
                .add("times", times)
                .add("verified", verified)
                .add("codeType", codeType)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .toString();
    }
}