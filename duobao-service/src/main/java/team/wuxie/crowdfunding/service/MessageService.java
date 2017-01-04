package team.wuxie.crowdfunding.service;

import java.util.List;
import java.util.Map;

import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.domain.enums.MessageType;
import team.wuxie.crowdfunding.util.service.BaseService;

/**
 * <p>
 * 消息Service
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:28
 */
public interface MessageService extends BaseService<TMessage> {
	List<TMessage> selectByUserIdAndType(Integer userId,MessageType messageType, Integer pageNum,
			Integer pageSize);

	/**
	 * 查询指定用户每种类型下第一条的消息记录
	 * key:消息类型 value:消息实体
	 * @author fly
	 * @param userId
	 * @return
	 * @since
	 */
	Map<Integer,TMessage> selectTopMessageByUserId(Integer userId);
	
	int readMessage(Integer userId,Integer messageId);
	
	int unReadCount(Integer userId);
	
	/**
	 * 新增一条消息并推送给用户
	 * @author fly
	 * @param message
	 * @return  
	 * @since
	 */
	int addAndPush(TMessage message);
}
