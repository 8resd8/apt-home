package com.ssafy.home.global.config;

import com.ssafy.home.global.interceptor.BrokerInterceptor;
import com.ssafy.home.global.interceptor.AuthLoginResolver;
import com.ssafy.home.global.interceptor.LoginInterceptor;
import com.ssafy.home.global.interceptor.MemberInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
                .excludePathPatterns("/api/auth/**",  "/", "/estate/view/**");

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
}
