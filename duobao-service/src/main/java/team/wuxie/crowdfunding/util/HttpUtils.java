package team.wuxie.crowdfunding.util;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * ClassName:HttpUtils <br/>
 * 
 * @author fly
 * @version 1.0
 * @since 2016年10月31日 下午3:19:55
 * @see
 */
public class HttpUtils {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);
	
	public static CloseableHttpClient CLIENT;
	
	public static String ENCODING_UTF_8 = "UTF-8";

	public final static int TIME_OUT = 7000;

	/**
	 * 连接池最大连接数量
	 */
	public final static int POOL_SIZE = 45;

	/**
	 * 单个URL最大连接数量
	 */
	public final static int MAX_PERROUTE = 15;
	
	public final static String SINA_IP_ADDRESS_URL = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";

	static {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(POOL_SIZE);
		cm.setDefaultMaxPerRoute(MAX_PERROUTE);
		HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount >= 5) {// 如果已经重试了5次，就放弃
					return false;
				}
				if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
					return false;
				}
				if (exception instanceof InterruptedIOException) {// 超时
					return false;
				}
				if (exception instanceof UnknownHostException) {// 目标服务器不可达
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
					return false;
				}
				if (exception instanceof SSLException) {// SSL握手异常
					return false;
				}

				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				// 如果请求是幂等的，就再次尝试
				if (!(request instanceof HttpEntityEnclosingRequest)) {
					return true;
				}
				return false;
			}
		};
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(TIME_OUT)
				.setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
		CLIENT = HttpClients.custom().setConnectionManager(cm).setRetryHandler(httpRequestRetryHandler)
				.setDefaultRequestConfig(requestConfig).build();
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new IdleConnectionMonitor(cm), 0, 30, TimeUnit.SECONDS);
	}

	static class IdleConnectionMonitor implements Runnable {
		PoolingHttpClientConnectionManager connectionManager;

		public IdleConnectionMonitor(PoolingHttpClientConnectionManager connectionManager) {
			this.connectionManager = connectionManager;
		}

		@Override
		public void run() {
			connectionManager.closeExpiredConnections();
			connectionManager.closeIdleConnections(30, TimeUnit.SECONDS);
		}
	}
	
	public static String sendGet(String uri) {
		HttpGet get = new HttpGet(uri);
		HttpResponse response = null;
		try {
			response = CLIENT.execute(get);
			if (response != null) {
				int code = response.getStatusLine().getStatusCode();

				if (code == 200) {
					String result = EntityUtils.toString(response.getEntity(), ENCODING_UTF_8);
					return result;
				} else {
					LOGGER.error("Request http error,url: " + uri);
				}
			}

		} catch (Exception e) {
			LOGGER.error("", e);
		} finally {
			try {
				EntityUtils.consume(response.getEntity());
			} catch (Exception e) {
				e.printStackTrace();
			}
			get.abort();
		}
		return null;
	}
	
	/**
	 * 根据IP地址获取城市名
	 * @author fly
	 * @param ip
	 * @return  
	 * @since
	 */
	public static String getCityByIp(String ip){
		String jsonResult = sendGet(SINA_IP_ADDRESS_URL+ip);
		if(StringUtil.isNotEmpty(jsonResult) && jsonResult.contains("{")){
			JSONObject jsonObject = JSON.parseObject(jsonResult);
			return jsonObject.getString("city");
		}
		return "";
	}
}
