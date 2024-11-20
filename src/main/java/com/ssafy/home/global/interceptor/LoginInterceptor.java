package com.ssafy.home.global.interceptor;

import com.ssafy.home.global.enums.Session;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // preflight 응답
        if(HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            return true;
        }

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(Session.TYPE.name()) == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
            return false;
        }

        return true;
    }
}