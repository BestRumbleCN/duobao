package team.wuxie.crowdfunding.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import team.wuxie.crowdfunding.domain.enums.BannerType;
import team.wuxie.crowdfunding.domain.enums.MessageType;
import team.wuxie.crowdfunding.domain.enums.ShippingStatus;
import team.wuxie.crowdfunding.util.fastjson.BannerTypeSerializer;
import team.wuxie.crowdfunding.util.fastjson.MessageTypeSerializer;
import team.wuxie.crowdfunding.util.fastjson.ShippingStatusSerializer;

/**
 * @author WuGang
 * @since 1.0
 */
public class FastJsonSerializeConfig extends SerializeConfig {

    public FastJsonSerializeConfig() {
        super.put(ShippingStatus.class, new ShippingStatusSerializer());
        super.put(BannerType.class, new BannerTypeSerializer());
        super.put(MessageType.class, new MessageTypeSerializer());
       // super.put(PocketStatus.class, new PocketStatusSerializer());
    }
}
