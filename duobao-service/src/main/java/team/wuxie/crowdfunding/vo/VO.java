package team.wuxie.crowdfunding.vo;

import com.alibaba.fastjson.JSON;
import team.wuxie.crowdfunding.util.fastjson.SerializerFeatures;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wushige
 * @date 2016-08-11 10:46
 */
public class VO implements Serializable {
    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeatures.features);
    }
}
