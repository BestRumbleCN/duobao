package team.wuxie.crowdfunding.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.util.mybatis.mapper.BaseMapper;

public interface TMessageMapper extends BaseMapper<TMessage> {

	List<TMessage> selectByUserIdAndType(@Param("userId") Integer userId,
			@Param("messageType") Integer messageType);

	/**
	 * 查询指定用户每种类型下第一条的消息记录
	 * 
	 * @author fly
	 * @param userId
	 * @return
	 * @since
	 */
	List<TMessage> selectTopMessageByUserId(@Param("userId") Integer userId);
	
	/**
	 * 查询最新一条系统消息
	 * @author fly
	 * @return  
	 * @since
	 */
	TMessage selectTopSystemMessage();
	
	/**
	 * 读取消息
	 * @author fly
	 * @param userId
	 * @param messageId
	 * @return  
	 * @since
	 */
	int readMessage(@Param("userId")Integer userId,@Param("messageId")Integer messageId);
	
	/**
	 * 查询用户未读消息数量
	 * @author fly
	 * @param userId
	 * @return  
	 * @since
	 */
	int unReadCount(@Param("userId") Integer userId);
}