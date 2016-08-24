package team.wuxie.crowdfunding.util;

import com.google.common.base.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 正则util
 * </p>
 *
 * @author wushige
 * @date 2016-08-24 18:15
 */
public class RegexUtil {
    /**
     * 验证是否是手机号码
     *
     * @param cellphone
     * @return
     */
    public static boolean isCellphone(String cellphone) {
        if (Strings.isNullOrEmpty(cellphone)) return false;
        Pattern p = Pattern.compile("^((1[3-9][0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(cellphone);
        return m.matches();
    }
}
