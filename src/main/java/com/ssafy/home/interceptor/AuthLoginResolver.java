package com.ssafy.home.interceptor;

import com.ssafy.home.annotation.Login;
import com.ssafy.home.auth.domain.Broker;
import com.ssafy.home.auth.domain.Member;
import com.ssafy.home.enums.Session;
import com.ssafy.home.enums.UserType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthLoginResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean member = Member.class.equals(parameter.getParameterType());
        boolean broker = Broker.class.equals(parameter.getParameterType());

        return hasLoginAnnotation && (member || broker);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(Session.TYPE.name()) == null) {
            return null;
        }

        String type = (String) session.getAttribute(Session.TYPE.name()); // 로그인 타입 조회

        // 브로커인 경우 브로커 아이디 꺼냄
        if (type.equals(UserType.BROKER.name()) && Broker.class.equals(parameter.getParameterType())) {
            return (Broker) session.getAttribute(Session.BROKER_ID.name());
        }

        // 멤버인 경우 멤버 아이디 꺼냄
        if (type.equals(UserType.MEMBER.name()) && Member.class.equals(parameter.getParameterType())) {
            return (Member) session.getAttribute(Session.MEMBER_ID.name());
        }

        return null;
    }
}