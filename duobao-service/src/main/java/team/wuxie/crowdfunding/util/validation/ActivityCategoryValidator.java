package team.wuxie.crowdfunding.util.validation;

import com.google.common.collect.ImmutableList;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import team.wuxie.crowdfunding.domain.TActivityCategory;

import java.util.List;

import static team.wuxie.crowdfunding.domain.TActivityCategory.PROP_NAME;

/**
 * @author WuGang
 * @since 1.0
 */
public class ActivityCategoryValidator implements Validator {

    private final List<String> requiredFields;

    public ActivityCategoryValidator() {
        this(ImmutableList.of(PROP_NAME));
    }

    public ActivityCategoryValidator(List<String> requiredFields) {
        this.requiredFields = requiredFields;
    }

    public static ActivityCategoryValidator validator() {
        return new ActivityCategoryValidator(ImmutableList.of());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TActivityCategory.class.equals(clazz) || TActivityCategory.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TActivityCategory activityCategory = (TActivityCategory) target;

        if (checkRequired(PROP_NAME)) {
            ValidationUtils.rejectIfEmpty(errors, PROP_NAME, "activityCategory.v.name_required", "请填写分类名称");
        }
    }

    public List<String> getRequiredFields() {
        return requiredFields;
    }

    public boolean checkRequired(String filed) {
        return requiredFields.contains(filed) || requiredFields.isEmpty();
    }
}
