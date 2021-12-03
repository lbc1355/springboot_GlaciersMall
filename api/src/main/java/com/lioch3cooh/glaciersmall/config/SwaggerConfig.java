package com.lioch3cooh.glaciersmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket getDocket() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        ApiInfo build = apiInfoBuilder.title("后端接口说明")
                .description("后端接口规范")
                .contact(new Contact("lioch3cooh", "lioch3cooh.top", "lbc1355@163.com"))
                .version("v 1.0.1")
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)//指定文档风格
                .apiInfo(build)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lioch3cooh.glaciersmall.controller"))
                .build();
        return docket;
    }
}
