package team.wuxie.crowdfunding.domain;

import com.google.common.base.MoreObjects;
import team.wuxie.crowdfunding.domain.enums.MessageType;
import team.wuxie.crowdfunding.util.mybatis.typehandler.MessageTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 消息表
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:34
 */
@SuppressWarnings("unused")
@Table(name = "t_message")
public class TMessage implements Serializable {

    private static final long serialVersionUID = 1577196232607841291L;

    /**
     * 消息ID
     */
    @Id
    @Column(name = "message_id")
    private Integer messageId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 消息类型：0-活动、1-中奖、2-发货、3-客服、4-系统
     */
    @Column(name = "message_type")
    @ColumnType(typeHandler = MessageTypeHandler.class)
    private MessageType messageType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    public TMessage(Integer messageId, Integer userId, String title, String content, MessageType messageType, Date createTime) {
        this.messageId = messageId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.messageType = messageType;
        this.createTime = createTime;
    }

    public TMessage() {
    }

    /**
     * 获取消息ID
     *
     * @return message_id - 消息ID
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * 设置消息ID
     *
     * @param messageId 消息ID
     */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
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
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取消息类型：0-活动、1-中奖、2-发货、3-客服、4-系统
     *
     * @return message_type - 消息类型：0-活动、1-中奖、2-发货、3-客服、4-系统
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     * 设置消息类型：0-活动、1-中奖、2-发货、3-客服、4-系统
     *
     * @param messageType 消息类型：0-活动、1-中奖、2-发货、3-客服、4-系统
     */
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("messageId", messageId)
                .add("userId", userId)
                .add("title", title)
                .add("content", content)
                .add("messageType", messageType)
                .add("createTime", createTime)
                .toString();
    }
}