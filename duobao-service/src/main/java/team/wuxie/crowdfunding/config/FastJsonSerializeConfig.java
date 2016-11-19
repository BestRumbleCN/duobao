package team.wuxie.crowdfunding.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import team.wuxie.crowdfunding.domain.ShippingStatus;
import team.wuxie.crowdfunding.util.fastjson.ShippingStatusSerializer;

/**
 * @author WuGang
 * @since 1.0
 */
public class FastJsonSerializeConfig extends SerializeConfig {

    public FastJsonSerializeConfig() {
        super.put(ShippingStatus.class, new ShippingStatusSerializer());
    }
}
