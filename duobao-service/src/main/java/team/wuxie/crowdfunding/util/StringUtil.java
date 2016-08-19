package team.wuxie.crowdfunding.util;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 字符串处理util
 * </p>
 *
 * @author wushige
 * @date 2016-08-19 17:03
 */
@SuppressWarnings("unused")
public class StringUtil {

    public static final String DEFAULT_SEPARATOR = ";";

    public StringUtil() {
    }

    /**
     * 根据分隔符将字符串解析成字符数组
     *
     * @param text
     * @param separator
     * @return
     */
    public static List<String> parseWithSeparator(String text, String separator) {
        if (Strings.isNullOrEmpty(text)) return null;

        List<String> list = new ArrayList<>();
        if (Strings.isNullOrEmpty(separator)) separator = DEFAULT_SEPARATOR;
        String[] textList = text.trim().split(separator);
        Collections.addAll(list, textList);
        return list;
    }

    /**
     * 将字符串数字拼接成字符串
     *
     * @param strings   字符串数字
     * @param separator 分隔符
     * @return
     */
    public static String combineWithSeparator(String[] strings, char separator) {
        if (strings == null || strings.length <= 0) return null;

        String result = "";
        for (String text : strings) {
            if (text == null || text.trim().length() == 0) continue;
            result += text.trim() + separator;
        }
        if (result.trim().length() > 0 && result.charAt(result.length() - 1) == separator)
            result = result.substring(0, result.length() - 1);
        return result;
    }
}
