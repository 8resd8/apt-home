package com.ssafy.home.global.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder().group("all").pathsToMatch("/**").build();
    }

    @Bean
    public GroupedOpenApi brokerApi() {
        return GroupedOpenApi.builder().group("broker").pathsToMatch("/broker/**").build();
    }

    @Bean
    public GroupedOpenApi memberApi() {
        return GroupedOpenApi.builder().group("member").pathsToMatch("/member/**").build();
    }
}
