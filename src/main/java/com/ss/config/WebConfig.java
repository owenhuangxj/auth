package com.ss.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import jdk.nashorn.internal.runtime.QuotedStringTokenizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter messageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                SerializerFeature.QuoteFieldNames,                  // 输出key时使用双引号
                SerializerFeature.WriteMapNullValue,                // 输出值为null的字段
                SerializerFeature.WriteNullNumberAsZero,            // 数字字段为null时输出为0
                SerializerFeature.WriteNullListAsEmpty,             // 空List 输出为[]
                SerializerFeature.WriteNullStringAsEmpty,           // 空字符串输出为""
                SerializerFeature.WriteNullBooleanAsFalse,          // 空布尔值输出为false
                SerializerFeature.WriteDateUseDateFormat,           // 
                SerializerFeature.DisableCircularReferenceDetect    //
        );

    }
}
