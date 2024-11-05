package com.ssafy.home.global.config;

import com.ssafy.home.global.interceptor.AuthInterceptor;
import com.ssafy.home.global.interceptor.AuthLoginResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final AuthLoginResolver loginUserArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .order(1)
                .addPathPatterns("/member/**", "/broker/**")
                .excludePathPatterns("/api/auth/login", "/api/auth/signup/**", "/api/auth/logout",  "/", "/error", "/css/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver); // 커스텀 Argument Resolver 등록
    }
}
