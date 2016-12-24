package team.wuxie.crowdfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import team.wuxie.crowdfunding.controller.base.BaseController;
import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.domain.support.Messages;
import team.wuxie.crowdfunding.service.MessageService;
import team.wuxie.crowdfunding.util.ajax.AjaxResult;
import team.wuxie.crowdfunding.util.page.Page;
import team.wuxie.crowdfunding.util.validation.MessageValidator;
import team.wuxie.crowdfunding.util.validation.ValidationUtil;

import javax.validation.Valid;

/**
 * @author WuGang
 * @since 1.0
 */
@Controller
@RequestMapping("/messages")
public class MessagesController extends BaseController {

    @InitBinder("message")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(MessageValidator.validator());
    }

    @Autowired
    private MessageService messageService;

    /**
     * 加载消息列表视图
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String loadMessagesView() {
        return "message/message_list";
    }

    @RequestMapping(value = "/table.json", method = RequestMethod.GET)
    @ResponseBody
    public Page<TMessage> findMessagePage(String table) {
        return Messages.findMessagePage(table);
    }

    /**
     * 创建消息
     *
     * @param message
     * @param result
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult newMessage(@Valid @ModelAttribute("message") TMessage message,
                                BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResult.getFailure(ValidationUtil.getErrorMessage(result));
        }

        try {
            message.newMessage();
            messageService.insertSelective(message);
            return AjaxResult.getSuccess("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getFailure("添加失败");
        }
    }
}
