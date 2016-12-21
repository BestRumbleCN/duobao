package team.wuxie.crowdfunding.util.validation;

import com.google.common.collect.ImmutableList;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import team.wuxie.crowdfunding.domain.TMessage;

import java.util.List;

import static team.wuxie.crowdfunding.domain.TMessage.PROP_CONTENT;
import static team.wuxie.crowdfunding.domain.TMessage.PROP_MESSAGE_TYPE;
import static team.wuxie.crowdfunding.domain.TMessage.PROP_TITLE;

/**
 * @author WuGang
 * @since 1.0
 */
public class MessageValidator implements Validator {

    private final List<String> requiredFields;

    public MessageValidator() {
        this(ImmutableList.of(PROP_TITLE, PROP_CONTENT, PROP_MESSAGE_TYPE));
    }

    public MessageValidator(List<String> requiredFields) {
        this.requiredFields = requiredFields;
    }

    public static MessageValidator validator() {
        return new MessageValidator(ImmutableList.of());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TMessage.class.equals(clazz) || TMessage.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TMessage message = (TMessage) target;

        if (checkRequired(PROP_TITLE)) {
            ValidationUtils.rejectIfEmpty(errors, PROP_TITLE, "message.v.title_required", "请填写消息标题");
        }
        if (checkRequired(PROP_CONTENT)) {
            ValidationUtils.rejectIfEmpty(errors, PROP_CONTENT, "message.v.content_required", "请填写消息内容");
        }
        if (checkRequired(PROP_MESSAGE_TYPE)) {
            ValidationUtils.rejectIfEmpty(errors, PROP_MESSAGE_TYPE, "message.v.type_required", "请选择消息类型");
        }
    }

    public List<String> getRequiredFields() {
        return requiredFields;
    }

    public boolean checkRequired(String filed) {
        return requiredFields.contains(filed) || requiredFields.isEmpty();
    }
}
