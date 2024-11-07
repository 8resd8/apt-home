package com.ssafy.home.interceptor;

import com.ssafy.home.enums.Session;
import com.ssafy.home.enums.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class AuthLoginResolverTest {


    private MockHttpSession memberSession;
    private MockHttpSession brokerSession;

    @BeforeEach
    void setUp() {
        memberSession = new MockHttpSession();
        memberSession.setAttribute(Session.TYPE.name(), UserType.MEMBER.name());
        memberSession.setAttribute(Session.MEMBER_ID.name(), "member1");

        brokerSession = new MockHttpSession();
        brokerSession.setAttribute(Session.TYPE.name(), UserType.BROKER.name());
        brokerSession.setAttribute(Session.BROKER_ID.name(), "broker1");
    }

    @Test
    @DisplayName("브로커 세션에 BrokerId 확인")
    void brokerSession() {
        String brokerId = (String) brokerSession.getAttribute(Session.BROKER_ID.name());

        assertThat(brokerId).isEqualTo("broker1");
    }

    @Test
    @DisplayName("멤버 세션에 MemberId 확인")
    void memberSession() {
        String memberId = (String) memberSession.getAttribute(Session.MEMBER_ID.name());

        assertThat(memberId).isEqualTo("member1");
    }
}
