package com.ssafy.home.interceptor;

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

        if (session == null || session.getAttribute(Session.TYPE.name()) == null) {
            response.sendRedirect("/api/auth/login?redirectURL=" + requestURI);
            return false;
        }

        return true;
    }
}
