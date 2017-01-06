package team.wuxie.crowdfunding.mapper;

import org.apache.ibatis.annotations.Param;
import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.model.MessageQuery;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

import java.util.List;

public interface TMessageMapper extends BaseMapper<TMessage> {

    List<TMessage> selectByUserIdAndType(@Param("userId") Integer userId,
                                         @Param("messageType") Integer messageType);

    /**
     * 查询指定用户每种类型下第一条的消息记录
     *
     * @param userId
     * @return
     * @author fly
     */
    List<TMessage> selectTopMessageByUserId(@Param("userId") Integer userId);

    /**
     * 查询最新一条系统消息
     *
     * @return
     * @author fly
     */
    TMessage selectTopSystemMessage();

    /**
     * 读取消息
     *
     * @param userId
     * @param messageId
     * @return
     * @author fly
     */
    int readMessage(@Param("userId") Integer userId, @Param("messageId") Integer messageId);

    /**
     * 查询用户未读消息数量
     *
     * @param userId
     * @return
     * @author fly
     */
    int unReadCount(@Param("userId") Integer userId);

    List<TMessage> selectAllByQuery(@Param("query") MessageQuery query);
}