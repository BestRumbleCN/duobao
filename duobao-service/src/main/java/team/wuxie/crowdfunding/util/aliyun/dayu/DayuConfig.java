package team.wuxie.crowdfunding.util.aliyun.dayu;

/**
 * <p>
 * 大鱼短信服务配置
 * </p>
 *
 * @author wushige
 * @date 2016-08-24 16:44
 */
public class DayuConfig {


    //应用的AppKey，参数名称：X-Ca-Key
    public static final String APP_KEY = "23490117";
    //AppSecret，参数名称：X-Ca-Secret
    public static final String APP_SECRET = "1db55f7e5b003d193bfc0c4077899f96";

    //Base url
    public static final String BASE_URL = "http://gw.api.taobao.com/router/rest";

    //短信发送API
    public static final String SEND_SMS = "/sendSms?sms_type=normal&sms_free_sign_name=信誉夺宝&sms_template_code=SMS_585014";
    //短信发送记录查询API
    public static final String QUERY_SMS_DETAIL = "/querySmsDetail";

}
