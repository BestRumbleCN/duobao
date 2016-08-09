package team.wuxie.crowdfunding.util.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import team.wuxie.crowdfunding.domain.TUser;

/**
 * <p>
 * 用户验证器
 * </p>
 *
 * @author wushige
 * @date 2016-08-08 15:58
 */
public class UserValidator implements Validator {

    public UserValidator() {
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TUser user = (TUser) o;
        if (user.getUserId() == null) {
            ValidationUtils.rejectIfEmpty(errors, "username", null, "user.username_cannot_be_null");
            ValidationUtils.rejectIfEmpty(errors, "password", null, "user.password_cannot_be_null");
            ValidationUtils.rejectIfEmpty(errors, "role", null, "user.role_cannot_be_null");
        }
    }
}
