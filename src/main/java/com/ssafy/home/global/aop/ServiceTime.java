package com.ssafy.home.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ServiceTime {

    @Around("execution(* com.ssafy.home..service..*(..))")
    public Object serviceTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // ProceedingJoinPoint: 실제 메서드를 호출할 수 있는 객체
        // 메서드 이름, 시간 측청
        String fullPathClassName = joinPoint.getSignature().getDeclaringTypeName();
        String className = fullPathClassName.substring(fullPathClassName.lastIndexOf(".") + 1);
        String methodName = className + "." + joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();

        log.debug("[{}] - [START] - [요청 파라미터]: {}", methodName, args);

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            log.error("[{}] - [메서드 실행 중 예외 발생, 상세 내용: ]", methodName, e);
            throw e;
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
//        log.debug("[{}] - [END] - [실행 시간: {}ms] - [응답 값]: {}", methodName, executionTime, result.toString().replace(", ", "," + System.lineSeparator())); // DB 조회한 결과를 보려면 이걸로 하기
        log.debug("[{}] - [END] - [실행 시간: {}ms] - [응답 값]: {}\n", methodName, executionTime, result);

        return result;
    }

}
