package team.wuxie.crowdfunding.util.aliyun.dayu;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>
 * 大鱼短信 Client
 * </p>
 *
 * @author wushige
 * @date 2016-08-24 17:48
 */
public class DayuClient {

    private DayuService.DayuApi dayuApi;

    private static DayuClient INSTANCE = new DayuClient();

    public static DayuClient getClient() {
        return INSTANCE;
    }

    private DayuClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DayuConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dayuApi = retrofit.create(DayuService.DayuApi.class);
    }

    public DayuService.DayuApi getDayuApi() {
        return dayuApi;
    }
}
