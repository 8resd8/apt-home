package com.ssafy.home.interceptor;

import com.ssafy.home.enums.Session;
import com.ssafy.home.enums.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
class AuthInterceptorTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private MockHttpSession memberSession;
    private MockHttpSession brokerSession;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        // 멤버 세션 설정
        memberSession = new MockHttpSession();
        memberSession.setAttribute(Session.TYPE.name(), UserType.MEMBER.name());

        // 브로커 세션 설정
        brokerSession = new MockHttpSession();
        brokerSession.setAttribute(Session.TYPE.name(), UserType.BROKER.name());
    }

    @Test
    @DisplayName("멤버는 멤버 전용 경로에 접근 가능")
    void memberCanAccessMemberPath() throws Exception {
        mockMvc.perform(post("/member/some-path")
                        .session(memberSession))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("멤버는 브로커 전용 경로에 접근 불가")
    void memberCannotAccessBrokerPath() throws Exception {
        mockMvc.perform(post("/broker/some-path")
                        .session(memberSession))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("브로커는 브로커 전용 경로에 접근 가능")
    void brokerCanAccessBrokerPath() throws Exception {
        mockMvc.perform(post("/broker/some-path")
                        .session(brokerSession))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("브로커는 멤버 전용 경로에 접근 불가")
    void brokerCannotAccessMemberPath() throws Exception {
        mockMvc.perform(post("/member/some-path")
                        .session(brokerSession))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("비로그인 사용자는 멤버 경로에 접근 불가")
    void guestCannotAccessMemberPath() throws Exception {
        mockMvc.perform(post("/member/some-path"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("비로그인 사용자는 브로커 경로에 접근 불가")
    void guestCannotAccessBrokerPath() throws Exception {
        mockMvc.perform(post("/broker/some-path"))
                .andExpect(status().is3xxRedirection());
    }
}
