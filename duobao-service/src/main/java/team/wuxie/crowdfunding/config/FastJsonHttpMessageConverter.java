package team.wuxie.crowdfunding.config;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

/**
 * <p>
 * 使用fastjson httpMessageConverter
 * </p>
 *
 * @author wushige
 * @date 2016-07-04 12:18
 */
@Configuration
@ConditionalOnClass({FastJsonHttpMessageConverter4.class})
@ConditionalOnProperty(
        name = {"spring.http.converters.preferred-json-mapper"},
        havingValue = "fastjson",
        matchIfMissing = true
)
public class FastJsonHttpMessageConverter {
    public FastJsonHttpMessageConverter() {
    }

    @Bean
    @ConditionalOnMissingBean({FastJsonHttpMessageConverter4.class})
    public FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter4() {
        FastJsonHttpMessageConverter4 converter4 = new FastJsonHttpMessageConverter4(){
        	@Override
        	protected void writeInternal(Object t, Type type, HttpOutputMessage outputMessage) throws IOException,
        			HttpMessageNotWritableException {
        		if(t instanceof String){
        			String value = (String) t;
        			if(value.contains("<xml>") || value.equals("success") || value.equals("failue")){
        				StreamUtils.copy((String)t, Charset.forName("UTF-8"), outputMessage.getBody());
        			}
        		}else{
        			super.writeInternal(t, type, outputMessage);
        		}
        	}
        };
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue,
               // SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse,
                //SerializerFeature.PrettyFormat,
                //SerializerFeature.SortField,
                SerializerFeature.DisableCircularReferenceDetect
        );
        //添加自定义的Serializer
        fastJsonConfig.setSerializeConfig(new FastJsonSerializeConfig());
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm");
        converter4.setFastJsonConfig(fastJsonConfig);
        return converter4;
    }
}
