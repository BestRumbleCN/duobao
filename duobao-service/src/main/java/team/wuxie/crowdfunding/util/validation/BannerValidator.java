package team.wuxie.crowdfunding.util.validation;

import com.google.common.collect.ImmutableList;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import team.wuxie.crowdfunding.domain.TBanner;

import java.util.List;

import static team.wuxie.crowdfunding.domain.TBanner.PROP_BANNER_TYPE;

/**
 * @author WuGang
 * @since 1.0
 */
public class BannerValidator implements Validator {

    private final List<String> requiredFields;

    public BannerValidator() {
        this(ImmutableList.of(PROP_BANNER_TYPE));
    }

    public BannerValidator(List<String> requiredFields) {
        this.requiredFields = requiredFields;
    }

    public static BannerValidator validator() {
        return new BannerValidator(ImmutableList.of());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TBanner.class.equals(clazz) || TBanner.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TBanner banner = (TBanner) target;

        if (checkRequired(PROP_BANNER_TYPE)) {
            ValidationUtils.rejectIfEmpty(errors, PROP_BANNER_TYPE, "banner.v.bannerType_required", "请选择Banner类型");
        }
    }

    public List<String> getRequiredFields() {
        return requiredFields;
    }

    public boolean checkRequired(String filed) {
        return requiredFields.contains(filed) || requiredFields.isEmpty();
    }

}
