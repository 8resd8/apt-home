package com.ssafy.home.global.interceptor;

import com.ssafy.home.global.enums.Session;
import com.ssafy.home.global.enums.UserType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MemberInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // preflight 응답
        if(HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            return true;
        }

        HttpSession session = request.getSession(false);

        String type = (String) session.getAttribute(Session.TYPE.name());

        if (!UserType.MEMBER.name().equals(type)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "멤버만 접근할 수 있습니다.");
            return false;
        }

        return true;
    }
}