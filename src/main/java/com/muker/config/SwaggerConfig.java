package com.muker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration  //配置文件
public class SwaggerConfig {

    //创建文档说明
    @Bean
    public ApiInfo createAI() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("muker").description("慕课网  在线学习视频")
                .contact(new Contact("第四组", "http://www.17feri.top",
                        "1844167725@qq..com")).build();
        return apiInfo;
    }

    @Bean
    //创建Swagger扫描信息
    public Docket createD() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(createAI()).select()
                .apis(RequestHandlerSelectors.basePackage("com.muker.controller")).build();
    }
}
