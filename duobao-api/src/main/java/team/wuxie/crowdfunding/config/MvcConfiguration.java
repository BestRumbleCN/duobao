package team.wuxie.crowdfunding.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wushige
 * @date 2016-06-29 19:09
 */
@Configuration
@Import(Swagger2Config.class)
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        super.addResourceHandlers(registry);
    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    	List<MediaType> mediaType = new ArrayList<>();
//        mediaType.add(MediaType.TEXT_XML);
//        
//        MappingJackson2XmlHttpMessageConverter xmlConverter = new MappingJackson2XmlHttpMessageConverter();
//        xmlConverter.setSupportedMediaTypes(mediaType);
//
//        converters.add(xmlConverter);
    	super.configureMessageConverters(converters);
    }
}
