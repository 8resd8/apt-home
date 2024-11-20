package com.ssafy.home.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Aspect
@Component
public class LogAspect {

    // 1. 모든 컨트롤러 시작 전 로그 남기기
    @Before("execution(* com.ssafy.home..controller..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        if (args.length == 0) {
            log.info("[{}] - [START]\n", methodName);
            return;
        }
        log.info("[{}] - [START] - [요청 파라미터]: {}\n", methodName, args);
    }

    // RestController의 응답 값
    @AfterReturning(pointcut = "execution(* com.ssafy.home..controller..*(..))", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();

        // Controller의 응답 값 (Model 값)
        if (result instanceof ModelAndView modelAndView) {
            log.info("[{}] - [END] - 뷰 이름: {}, 모델 데이터: {}\n", name, modelAndView.getViewName(), modelAndView.getModel());
            return;
        }

        // RestController의 응답 값 (실제 값)
        log.info("[{}] - [END] - 응답 값: {}", name, result);
    }


    @Before("execution(* com.ssafy.home.global.interceptor..*.preHandle(..))")
    public void logInterceptorPreHandle(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("[{}] - [Interceptor START] - [요청 파라미터]: {}", methodName, args);
    }

    @After("execution(* com.ssafy.home.global.interceptor..*.postHandle(..))")
    public void logInterceptorPostHandle(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("[{}] - [Interceptor END]", methodName);
    }

}
