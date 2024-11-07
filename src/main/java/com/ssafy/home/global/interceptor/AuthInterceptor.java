package com.ssafy.home.global.interceptor;

import com.ssafy.home.global.enums.Session;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String requestURI = request.getRequestURI();

        // 로그인 여부 확인
        if (session == null || session.getAttribute(Session.TYPE.name()) == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
            return false;
        }

        // 사용자 유형 확인
        String type = (String) session.getAttribute(Session.TYPE.name());

        // 멤버는 /broker 경로 갈 수 없음
        if (UserType.MEMBER.name().equals(type) && requestURI.startsWith("/broker")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "접근 권한이 없습니다.");
            return false;
        }

        // 브로커는 /member 경로 갈 수 없음
        if (UserType.BROKER.name().equals(type) && requestURI.startsWith("/member")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "접근 권한이 없습니다.");
            return false;
        }

        return true;
    }
}