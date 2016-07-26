package team.wuxie.crowdfunding.util.ajax;

import com.alibaba.fastjson.JSON;
import team.wuxie.crowdfunding.util.fastjson.SerializerFeatures;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 定义Ajax返回结果实体类
 * </p>
 *
 * @author wushige
 * @date 2016-06-30 12:26
 */
public class AjaxResult implements Serializable {

    /**
     * Ajax操作返回的状态。
     */
    private int status;
    /**
     * Ajax操作返回的信息。
     */
    private String message;
    /**
     * Ajax操作返回的一组数据。
     */
    private Map<String, String> valueMap;
    /**
     * Ajax操作返回的后续动作名称。
     */
    private String action;
    /**
     * Ajax操作返回的需要跳转的视图名称。跳转方式由action属性指定。
     */
    private String view;
    /**
     * 返回数据
     */
    private Object data;

    public static AjaxResult getSuccess() {
        return getSuccess(null);
    }

    public static AjaxResult getSuccess(String message) {
        return getSuccess(message, null);
    }

    public static AjaxResult getSuccess(Object data) {
        return getSuccess(null, null, null, null, data);
    }

    public static AjaxResult getSuccess(String message, String view) {
        return getSuccess(message, null, null, view, null);
    }

    public static AjaxResult getSuccess(String message, String action, String view) {
        return getSuccess(message,null, action, view);
    }

    public static AjaxResult getSuccess(String message, Map<String, String> valueMap, String action, String view) {
        return getSuccess(message, valueMap, action, view, null);
    }

    public static AjaxResult getSuccess(String message, Map<String, String> valueMap, String action, String view, Object data) {
        return getResponse(Status.SUCCESS, message, valueMap, action, view, data);
    }

    public static AjaxResult getFailure(String message) {
        return getResponse(Status.FAILURE, message, null, null, null, null);
    }

    public static AjaxResult getFailure(String message, Map<String, String> valueMap) {
        return getResponse(Status.FAILURE, message, valueMap, null, null, null);
    }

    public static AjaxResult redirectTo(String view) {
        return getResponse(Status.EXPIRE, null, null, null, view, null);
    }

    public static AjaxResult getResponse(Status status, String message, Map<String, String> valueMap, String action, String view, Object data) {
        return new Builder(status.getValue()).message(message).valueMap(valueMap).action(action).view(view).data(data).build();
    }

    private AjaxResult(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.valueMap = builder.valueMap;
        this.action = builder.action;
        this.view = builder.view;
        this.data = builder.data;
    }

    public static class Builder {
        //required parameters
        private int status;

        //optional parameters
        private String message;
        private Map<String, String> valueMap;
        private String action = "GET";
        private String view;
        private Object data;

        public Builder(int status) {
            this.status = status;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder valueMap(Map<String, String> valueMap) {
            this.valueMap = valueMap;
            return this;
        }

        public Builder action(String action) {
            this.action = action;
            return this;
        }

        public Builder view(String view) {
            this.view = view;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public AjaxResult build() {
            return new AjaxResult(this);
        }
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

    public Map<String, String> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<String, String> valueMap) {
        this.valueMap = valueMap;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
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
    EXPIRE(-1),
    /**
     * 成功
     */
    SUCCESS(1),
    /**
     * 出错
     */
    FAILURE(0);

    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
