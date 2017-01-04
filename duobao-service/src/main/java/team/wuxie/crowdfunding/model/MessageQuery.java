package team.wuxie.crowdfunding.model;

import com.google.common.base.MoreObjects;
import team.wuxie.crowdfunding.domain.enums.MessageType;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wushige
 * @date 2017-01-04 23:27
 */
public class MessageQuery implements Serializable {

    private Integer userId;
    private MessageType messageType;
    private String username;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("messageType", messageType)
                .add("username", username)
                .toString();
    }
}