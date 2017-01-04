package team.wuxie.crowdfunding.event.message;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wushige
 * @date 2017-01-04 23:42
 */
public class MessageSendingEvent extends MessageEvent {

    public static final String ACTION_NAME = "sending";

    public MessageSendingEvent(Object source) {
        super(source, ACTION_NAME);
    }
}
