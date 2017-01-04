package team.wuxie.crowdfunding.event.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;
import team.wuxie.crowdfunding.domain.TMessage;
import team.wuxie.crowdfunding.service.MessageService;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wushige
 * @date 2017-01-04 23:43
 */
@Component
public class MessageEventListener implements SmartApplicationListener {

    @Autowired
    private MessageService messageService;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return MessageEvent.class.equals(eventType) || MessageEvent.class.isAssignableFrom(eventType);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return TMessage.class.equals(sourceType) || TMessage.class.isAssignableFrom(sourceType);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        MessageEvent messageEvent = (MessageEvent) event;
        TMessage message = messageEvent.getMessage();
        if (MessageSendingEvent.ACTION_NAME.equals(messageEvent.getAction())) {
            handleSendingMessage(message);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private void handleSendingMessage(TMessage message) {
        messageService.addAndPush(message);
    }
}
