package team.wuxie.crowdfunding.util.fastjson;

import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * <p>
 * 定义FastJson的序列号特性
 * </p>
 *
 * @author wushige
 * @date 2016-05-06 17:34
 */
public class SerializerFeatures {
    public static SerializerFeature[] features = {
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullBooleanAsFalse,
            //SerializerFeature.PrettyFormat,
            //SerializerFeature.SortField,
            SerializerFeature.DisableCircularReferenceDetect
    };
}
