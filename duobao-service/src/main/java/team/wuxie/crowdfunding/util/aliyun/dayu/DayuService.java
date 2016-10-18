package team.wuxie.crowdfunding.util.aliyun.dayu;

import com.alibaba.fastjson.JSON;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.io.IOException;

/**
 * <p>
 * 短信服务Service
 * </p>
 *
 * @author wushige
 * @date 2016-08-24 16:56
 */
public final class DayuService {

    /**
     * 发送短信响应结果
     */
    public static class Result {
        public final String code;
        public final String model;
        public final Boolean success;
        public final String msg;

        public Result(String code, String model, Boolean success, String msg) {
            this.code = code;
            this.model = model;
            this.success = success;
            this.msg = msg;
        }
    }

    public interface DayuApi {

        @Headers({
            "X-Ca-Key:" + DayuConfig.APP_KEY,
            "X-Ca-Secret:" + DayuConfig.APP_SECRET
        })
        @GET(DayuConfig.SEND_SMS)
        Call<Result> sendSms(@Query("rec_num") String cellphone,
                             @Query("sms_param") String smsParam);
    }

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DayuConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DayuApi dayuApi = retrofit.create(DayuApi.class);
        Call<Result> call = dayuApi.sendSms("13023179387", String.format("{\"code\": \"%s\"}", "123456"));
        Result result = call.execute().body();
        System.out.println(JSON.toJSONString(result));
    }

//    public static void main(String[] args) {
//        System.out.println(String.format("{\"code\": \"%s\"}", "123456"));
//    }
}
