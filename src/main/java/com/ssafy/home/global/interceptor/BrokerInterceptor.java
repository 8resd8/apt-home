package com.ssafy.home.global.interceptor;

import com.ssafy.home.global.enums.Session;
import com.ssafy.home.global.enums.UserType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class BrokerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String requestURI = request.getRequestURI();

        String type = (String) session.getAttribute(Session.TYPE.name());

        if (UserType.BROKER.name().equals(type) && requestURI.startsWith("/member")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "접근 권한이 없습니다.");
            return false;
        }

        return true;
    }
}