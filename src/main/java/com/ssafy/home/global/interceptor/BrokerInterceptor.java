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

        String type = (String) session.getAttribute(Session.TYPE.name());

        if (!UserType.BROKER.name().equals(type)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "브로커만 접근할 수 있습니다.");
            return false;
        }

        return true;
    }
}