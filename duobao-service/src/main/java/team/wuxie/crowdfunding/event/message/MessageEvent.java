package team.wuxie.crowdfunding.event.message;

import com.google.common.collect.ImmutableList;
import org.springframework.context.ApplicationEvent;
import team.wuxie.crowdfunding.domain.TMessage;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wushige
 * @date 2017-01-04 23:39
 */
public class MessageEvent extends ApplicationEvent {

    /**
     * The Event action name. like CREATED, MODIFIED, RESET_PASSWORD etc.
     */
    private final String action;
    private final List<String> changedProps;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MessageEvent(Object source, String action) {
        super(source);
        this.action = checkNotNull(action, "action");
        this.changedProps = ImmutableList.of();
    }

    public MessageEvent(Object source, String action, List<String> changedProps) {
        super(source);
        this.action = checkNotNull(action, "action");
        this.changedProps = checkNotNull(changedProps, "changedProps");
    }

    public TMessage getMessage() {
        return (TMessage) getSource();
    }

    public String getAction() {
        return action;
    }

    public List<String> getChangedProps() {
        return changedProps;
    }
}
