package com.jitlee.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${my.imgConnectPath}")
    private String connectPath;
    @Value("${my.externalStorage}")
    private String resourcePath;
    @Value("${my.tmpImgConnectPath}")
    private String tmpConnectPath;
    @Value("${my.externalTmpStorage}")
    private String tmpResourcePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(connectPath + "**")
                .addResourceLocations("file://" + resourcePath);
        registry.addResourceHandler(tmpConnectPath + "**")
                .addResourceLocations("file://" + tmpResourcePath);
    }
}
