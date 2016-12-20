package team.wuxie.crowdfunding.util.validation;

import com.google.common.collect.ImmutableList;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import team.wuxie.crowdfunding.domain.TUser;

import java.util.List;

import static team.wuxie.crowdfunding.domain.TUser.PROP_PASSWORD;
import static team.wuxie.crowdfunding.domain.TUser.PROP_USERNAME;

/**
 * <p>
 * 用户验证器
 * </p>
 *
 * @author wushige
 * @date 2016-08-08 15:58
 */
public class UserValidator implements Validator {

    private final List<String> requiredFields;

    public UserValidator() {
        this(ImmutableList.of(PROP_USERNAME, PROP_PASSWORD));
    }

    public UserValidator(List<String> requiredFields) {
        this.requiredFields = requiredFields;
    }

    public static UserValidator validator() {
        return new UserValidator(ImmutableList.of());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TUser.class.equals(aClass) || TUser.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TUser user = (TUser) o;
        if (user.getUserId() == null) { //添加
            if (checkRequired(PROP_USERNAME)) {
                ValidationUtils.rejectIfEmpty(errors, PROP_USERNAME, "user.v.username_required", "请填写用户名");
            }
            if (checkRequired(PROP_PASSWORD)) {
                ValidationUtils.rejectIfEmpty(errors, PROP_PASSWORD, "user.v.password_required", "请填写密码");
            }
        }
    }

    public List<String> getRequiredFields() {
        return requiredFields;
    }

    public boolean checkRequired(String filed) {
        return requiredFields.contains(filed) || requiredFields.isEmpty();
    }
}
