package team.wuxie.crowdfunding.util.i18n;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * <p>
 * 加载配置、以及国际化信息
 * </p>
 *
 * @author wushige
 * @date 2016-06-02 16:59
 */
public final class Resources {

	/**
	 * 国际化信息
	 */
	private static final Map<String, ResourceBundle> MESSAGES = new HashMap<>();

	/**
	 * 获取国际化信息
	 *
	 * @param key
	 *            key
	 * @param params
	 *            可变参数
	 * @return
	 */
	public static String getMessage(String key, Object... params) {
		Locale locale = LocaleContextHolder.getLocale();
		ResourceBundle message = MESSAGES.get(locale.getLanguage());
		if (message == null) {
			synchronized (MESSAGES) {
				message = MESSAGES.get(locale.getLanguage());
				if (message == null) {
					message = ResourceBundle.getBundle("i18n/messages", locale);
					MESSAGES.put(locale.getLanguage(), message);
				}
			}
		}
		String tempMsg;
		try {
			tempMsg = message.getString(key);
		} catch (Exception e) {
			return key;
		}

		if (params != null) {
			return String.format(tempMsg, params);
		}
		return tempMsg;
	}

	/**
	 * 清除国际化信息
	 */
	public static void flushMessage() {
		MESSAGES.clear();
	}
}
