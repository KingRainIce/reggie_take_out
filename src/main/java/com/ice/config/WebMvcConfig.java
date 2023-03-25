package com.ice.config;

import com.ice.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @Title: ReggieApplication
 * @Auth: Ice
 * @Date: 2023/3/23 22:14
 * @Version: 1.0
 * @Desc: Set up static resource mappings
 */

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("addResourceHandlers");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Create a message converter
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        // Set up the object converter, and the underlying layer uses Jackson to convert Java to json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        // Append the above message converter object to the transformer collection of the MVC framework
        converters.add(0,messageConverter);
        super.extendMessageConverters(converters);
    }
}
