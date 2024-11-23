package com.ssafy.home.global.config;

import com.ssafy.home.global.interceptor.BrokerInterceptor;
import com.ssafy.home.global.interceptor.AuthLoginResolver;
import com.ssafy.home.global.interceptor.LoginInterceptor;
import com.ssafy.home.global.interceptor.MemberInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final BrokerInterceptor brokerInterceptor;
    private final MemberInterceptor memberInterceptor;
    private final AuthLoginResolver loginUserArgumentResolver;
    private final LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/estate/**",
                        "/profile/password-set", "/region-code/**");

        registry.addInterceptor(memberInterceptor)
                .order(2)
                .addPathPatterns("/member/**");

        registry.addInterceptor(brokerInterceptor)
                .order(3)
                .addPathPatterns("/broker/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver); // 커스텀 Argument Resolver 등록
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:*") // Patterns을 통해 모든 localhost 허용
                .allowedOrigins("43.201.21.196")
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/profile_images/**")
                .addResourceLocations("file:src/main/resources/upload/profile_images/");
    }

}
