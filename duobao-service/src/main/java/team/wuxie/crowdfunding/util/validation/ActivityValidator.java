package team.wuxie.crowdfunding.util.validation;

import com.google.common.collect.ImmutableList;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import team.wuxie.crowdfunding.domain.TActivity;

import java.util.List;

import static team.wuxie.crowdfunding.domain.TActivity.*;

/**
 * @author WuGang
 * @since 1.0
 */
public class ActivityValidator implements Validator {

    private final List<String> requiredFields;

    public ActivityValidator() {
        this(ImmutableList.of(PROP_CATEGORY_ID, PROP_NAME, PROP_CONTENT));
    }

    public ActivityValidator(List<String> requiredFields) {
        this.requiredFields = requiredFields;
    }

    public static ActivityValidator validator() {
        return new ActivityValidator(ImmutableList.of());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TActivity.class.equals(clazz) || TActivity.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TActivity activity = (TActivity) target;
        if (checkRequired(PROP_CATEGORY_ID)) {
            ValidationUtils.rejectIfEmpty(errors, PROP_CATEGORY_ID, null, "activity.v.category_id_required");
        }
        if (checkRequired(PROP_NAME)) {
            ValidationUtils.rejectIfEmpty(errors, PROP_NAME, null, "activity.v.name_required");
        }
        if (checkRequired(PROP_CONTENT)) {
            ValidationUtils.rejectIfEmpty(errors, PROP_CONTENT, null, "activity.v.content_required");
        }
        if (activity.getActivityId() != null) {
            ValidationUtils.rejectIfEmpty(errors, PROP_ENABLED, null, "activity.v.enabled_required");
        }
    }

    public List<String> getRequiredFields() {
        return requiredFields;
    }

    public boolean checkRequired(String filed) {
        return requiredFields.contains(filed) || requiredFields.isEmpty();
    }
}
