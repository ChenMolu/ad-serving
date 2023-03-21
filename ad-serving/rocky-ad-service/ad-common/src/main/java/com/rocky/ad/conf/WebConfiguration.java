package com.rocky.ad.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    // SpringBoot主动提供的是多个消息转换器
    // 需要先进行清空，再添加需要的
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>>
                                                   converters) {
        //清空提供的转换器
        converters.clear();
        //添加需要的转换器，
        // MappingJackson2HttpMessageConverter实现将Java对象转换为JSON对象
        converters.add(new MappingJackson2HttpMessageConverter());

    }
}
