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
public class ApiResult implements Serializable {
    /**
     * 消息id
     */
    private MessageId messageId;
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
    private Object data = new JSONObject();

    private ApiResult (Builder builder) {
        this.messageId = builder.messageId;
        this.status = builder.status;
        this.message = builder.message;
        this.timestamp = builder.timestamp;
        this.data = builder.data;
    }

    public static ApiResult getSuccess(MessageId messageId) {
        return getSuccess(messageId, null);
    }

    public static ApiResult getSuccess(MessageId messageId, String message) {
        return getSuccess(messageId, message, null);
    }

    public static ApiResult getSuccess(MessageId messageId, String message, Object data) {
        return getResponse(messageId, Status.SUCCESS, message, data);
    }

    public static ApiResult getFailure(MessageId messageId, String message) {
        return getResponse(messageId, Status.FAILURE, message, null);
    }

    public static ApiResult getExpired(MessageId messageId) {
        return getResponse(messageId, Status.EXPIRE, Resources.getMessage(Status.EXPIRE.getName()), null);
    }

    public static ApiResult getResponse(MessageId messageId, Status status, String message, Object data) {
        return new Builder(messageId, status.getValue(), new Date()).message(message).data(data).build();
    }

    public static class Builder {
        //required parameters
        private MessageId messageId;
        private int status;
        private Date timestamp;

        //optional parameters
        private String message;
        private Object data = new JSONObject();

        public Builder(MessageId messageId, int status, Date timestamp) {
            this.messageId = messageId;
            this.status = status;
            this.timestamp = timestamp;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public ApiResult build() {
            return new ApiResult(this);
        }
    }

    public MessageId getMessageId() {
        return messageId;
    }

    public void setMessageId(MessageId messageId) {
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

    public Date gettimestamp() {
        return timestamp;
    }

    public void settimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
