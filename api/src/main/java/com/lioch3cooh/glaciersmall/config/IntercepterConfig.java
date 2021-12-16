package com.lioch3cooh.glaciersmall.config;

import com.lioch3cooh.glaciersmall.interceptor.CheckTokenInterCeptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

    @Autowired
    private CheckTokenInterCeptor checkTokenInterCeptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkTokenInterCeptor)
                .addPathPatterns("/member/**");
    }
}
