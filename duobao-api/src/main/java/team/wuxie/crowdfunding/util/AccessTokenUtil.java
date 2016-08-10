package team.wuxie.crowdfunding.util;

import javax.servlet.http.HttpServletRequest;

public class AccessTokenUtil {

	/**
	 * 从HTTP request 对象中获取 token
	 * @param httpRequest
	 * @return
	 */
	public static String getAccessToken(HttpServletRequest httpRequest) {
        String accessToken = httpRequest.getParameter("accessToken");
		if (accessToken == null) return null;
        return accessToken;
	}
	
	private AccessTokenUtil() {}
}
