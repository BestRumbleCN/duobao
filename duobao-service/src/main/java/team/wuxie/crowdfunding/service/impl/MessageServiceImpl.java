package team.wuxie.crowdfunding.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.domain.enums.MessageType;
import team.wuxie.crowdfunding.mapper.TMessageMapper;
import team.wuxie.crowdfunding.service.MessageService;
import team.wuxie.crowdfunding.util.service.AbstractService;

/**
 * <p>
 * 消息ServiceImpl
 * </p>
 *
 * @author wushige
 * @date 2016-08-12 13:29
 */
@Service
@Transactional
public class MessageServiceImpl extends AbstractService<TMessage> implements MessageService {

    @Autowired
    private TMessageMapper messageMapper;

	@Override
	public List<TMessage> selectByUserIdAndType(Integer userId, MessageType messageType, Integer pageNum,
			Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize, true, false);
		PageHelper.orderBy("message_id desc");
		return messageMapper.selectByUserIdAndType(userId, messageType.getValue());
	}

	@Override
	public Map<Integer,TMessage> selectTopMessageByUserId(Integer userId) {
		Map<Integer,TMessage> result = new HashMap<Integer, TMessage>();
		List<TMessage> messages = messageMapper.selectTopMessageByUserId(userId);
		for(TMessage message : messages){
			result.put(message.getMessageType().getValue(), message);
		}
		for(MessageType messType : MessageType.values()){
			TMessage message = result.get(messType.getValue());
			if(message == null){
				result.put(messType.getValue(), null);
			}
		}
		TMessage sysMessage = messageMapper.selectTopSystemMessage();
		result.put(MessageType.SYSTEM.getValue(), sysMessage);
		return result;
	}

	@Override
	public int readMessage(Integer userId, Integer messageId) {
		return messageMapper.readMessage(userId, messageId);
	}

	@Override
	public int unReadCount(Integer userId) {
		return messageMapper.unReadCount(userId);
	}

	@Override
	public int addAndPush(TMessage message) {
		//TODO
		return 0;
	}
}
