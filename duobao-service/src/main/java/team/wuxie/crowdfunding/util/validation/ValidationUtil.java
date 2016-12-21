package team.wuxie.crowdfunding.util.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

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
        StringBuilder message = new StringBuilder();
        for (ObjectError error : result.getAllErrors()) {
            message.append(error.getDefaultMessage()).append("\n");
        }
        return message.toString();
    }
}
