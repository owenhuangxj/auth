package com.ss.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableWebMvc//表示该类是做Spring MVC 配置的
@Configuration //表示将该类注册到Spring IoC容器中去
@ComponentScan(value = "com.ss.controller")
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("=========================addCorsMappings=========================");
        registry.addMapping("/**")
                .allowCredentials(true) // 是否将凭证和数据一起提交到服务器
                .allowedHeaders("*")    //
                .allowedOrigins("*")    // 本服务器的所有资源都可以被访问，默认也是所有资源
                .maxAge(600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("=========================addResourceHandlers=========================");
        // 以/resources/** 为格式的资源都映射到static目录下去
        registry.addResourceHandler("resources/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX.concat("/static/**"))
                .setCachePeriod(60*60*24*7);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        System.out.println("=========================configureMessageConverters=========================");
        FastJsonHttpMessageConverter messageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                SerializerFeature.QuoteFieldNames,                  // 输出key时使用双引号
                SerializerFeature.WriteMapNullValue,                // 输出值为null的字段
                SerializerFeature.WriteNullNumberAsZero,            // 数字字段为null时输出为0
                SerializerFeature.WriteNullListAsEmpty,             // 空List 输出为[]
                SerializerFeature.WriteNullStringAsEmpty,           // 空字符串输出为""
                SerializerFeature.WriteNullBooleanAsFalse,          // 空布尔值输出为false
                SerializerFeature.WriteDateUseDateFormat,           // 使用日期转化器
                SerializerFeature.DisableCircularReferenceDetect    // 禁止循环引用
        );
        messageConverter.setFastJsonConfig(config);
        converters.add(messageConverter);
    }
}
