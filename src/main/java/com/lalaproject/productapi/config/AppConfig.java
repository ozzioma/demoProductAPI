package com.lalaproject.productapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application-version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Lala Product API")
                        .version(appVersion)
                        .description(appDesciption)
                        .termsOfService("http://ozzy.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://ozzy.org")));
    }


}
