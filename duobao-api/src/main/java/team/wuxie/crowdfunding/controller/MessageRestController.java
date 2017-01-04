package team.wuxie.crowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import team.wuxie.crowdfunding.controller.base.BaseRestController;
import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.domain.enums.MessageType;
import team.wuxie.crowdfunding.service.MessageService;
import team.wuxie.crowdfunding.util.api.ApiResult;
import team.wuxie.crowdfunding.util.api.MessageId;

/**
 * ClassName:MessageRestController <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2017年1月4日 下午4:39:51
 * @see
 */
@RestController
@Api(value = "message - Controller", description = "消息接口")
@RequestMapping("/message")
public class MessageRestController extends BaseRestController {
	@Autowired
	private MessageService messageService;

	@ApiOperation("未读消息数量（DONE）")
	@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "/unreadCount", method = RequestMethod.GET)
	public ApiResult unreadCount() {
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, messageService.unReadCount(getUserId()));
	}

	@ApiOperation("标记已读消息（DONE）")
	@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "/{messageId}", method = RequestMethod.POST)
	public ApiResult readMessage(@PathVariable Integer messageId) {
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, messageService.readMessage(getUserId(), messageId));
	}

	@ApiOperation("消息首页（DONE）")
	@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public ApiResult homePage() {
		Map<String,TMessage> result = new HashMap<String,TMessage>();
		Map<Integer,TMessage> messages = messageService.selectTopMessageByUserId(getUserId());
		for(Integer messageType : messages.keySet()){
			result.put(messageType + "", messages.get(messageType));
		}
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, result);
	}

	@ApiOperation("根据类型查询消息列表（DONE）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "messageType", value = "消息类型：0-默认、1-系统、2-客服、3-活动、4-中奖、5-发货", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "每页显示个数", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "accessToken", value = "用户Token", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public ApiResult messages(Integer messageType, Integer pageNum, Integer pageSize) {
		return ApiResult.getSuccess(MessageId.GENERAL_SUCCESS, messageService.selectByUserIdAndType(getUserId(),
				MessageType.of(messageType, MessageType.SYSTEM), pageNum, pageSize));
	}
}
