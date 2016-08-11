package team.wuxie.crowdfunding.util.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import team.wuxie.crowdfunding.util.fastjson.SerializerFeatures;
import team.wuxie.crowdfunding.util.i18n.Resources;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 定义Api返回结果实体类
 * </p>
 *
 * @author wushige
 * @date 2016-06-30 12:25
 */
@SuppressWarnings("unused")
public class ApiResult<T> implements Serializable {
    /**
     * 消息id
     */
    private int messageId;
    /**
     * 消息状态
     */
    private int status;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 时间戳：Date 类型
     */
    private Date timestamp;
    /**
     * 返回数据
     */
    private T data;

    private ApiResult (Builder<T> builder) {
        this.messageId = builder.messageId;
        this.status = builder.status;
        this.message = builder.message;
        this.timestamp = builder.timestamp;
        this.data = builder.data;
    }

    public static ApiResult getSuccess(int messageId) {
        return getSuccess(messageId, null);
    }

    public static ApiResult getSuccess(int messageId, String message) {
        return getSuccess(messageId, message, null);
    }

    public static <T> ApiResult getSuccess(int messageId, String message, T data) {
        return getResponse(messageId, Status.SUCCESS, message, data);
    }

    public static ApiResult getFailure(int messageId, String message) {
        return getResponse(messageId, Status.FAILURE, message, null);
    }

    public static ApiResult getExpired(int messageId) {
        return getResponse(messageId, Status.EXPIRE, Resources.getMessage(Status.EXPIRE.getName()), null);
    }

    @SuppressWarnings("unchecked")
    public static <T> ApiResult getResponse(int messageId, Status status, String message, T data) {
        return new Builder<T>(messageId, status.getValue(), new Date()).message(message).data(data).build();
    }

    public static class Builder<T> {
        //required parameters
        private int messageId;
        private int status;
        private Date timestamp;

        //optional parameters
        private String message;
        private T data;

        public Builder(int messageId, int status, Date timestamp) {
            this.messageId = messageId;
            this.status = status;
            this.timestamp = timestamp;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        @SuppressWarnings("unchecked")
        public Builder<T> data(T data) {
            this.data = data == null ? (T) new JSONObject() : data;
            return this;
        }

        public ApiResult<T> build() {
            return new ApiResult<>(this);
        }
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeatures.features);
    }
}

enum Status {
    /**
     * session失效
     */
    EXPIRE(-1, "user.session_is_expired"),
    /**
     * 成功
     */
    SUCCESS(1, "operation.success"),
    /**
     * 出错
     */
    FAILURE(0, "operation.failure"),
    /**
     * 请求错误
     */
    BAD_REQUEST(400, "bad.request")
    ;

    private int value;
    private String name;

    Status(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
