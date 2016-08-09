package team.wuxie.crowdfunding.util.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import team.wuxie.crowdfunding.util.i18n.Resources;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wushige
 * @date 2016-08-08 16:25
 */
public class ValidationUtil {

    public static String getErrorMessage(BindingResult result) {
        String message = null;
        for (ObjectError error : result.getAllErrors()) {
            message = Resources.getMessage(error.getCode()) + "\n";
        }
        return message;
    }
}
