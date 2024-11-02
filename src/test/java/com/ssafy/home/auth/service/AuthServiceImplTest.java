package com.ssafy.home.auth.service;

import com.ssafy.home.auth.dto.*;
import com.ssafy.home.auth.exception.LoginFailedException;
import com.ssafy.home.auth.repository.BrokerMapper;
import com.ssafy.home.auth.repository.MemberMapper;
import com.ssafy.home.enums.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class AuthServiceImplTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private BrokerMapper brokerMapper;


    private RequestMemberSignUp memberSignUpDto;
    private RequestBrokerSignUp brokerSignUpDto;
    private MockHttpSession session;

    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        // 테스트용 RequestMemberSignUp 객체 생성
        memberSignUpDto = new RequestMemberSignUp(
                "member1",
                "password123",
                "member@test.com",
                "010-1234-5678",
                "John Doe"
        );

        // 테스트용 RequestBrokerSignUp 객체 생성
        brokerSignUpDto = new RequestBrokerSignUp(
                "broker1",
                "password456",
                "Test Office",
                "Jane Doe",
                "010-8765-4321",
                "Seoul",
                "123-45-6789",
                "broker@test.com"
        );
    }

    @AfterEach
    void clearSession() {
        session.clearAttributes();
    }

    @Test
    @DisplayName("Member 회원가입")
    void signUpMember() {
        // when
        ResponseSignUp response = authService.signUpMember(memberSignUpDto);

        // then
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo("member1");
        assertThat(response.email()).isEqualTo("member@test.com");

        // 실제 DB에서 확인
        assertThat(memberMapper.findById("member1")).isPresent();
    }

    @Test
    @DisplayName("Broker 회원가입")
    void signUpBroker() {
        // when
        ResponseSignUp response = authService.signUpBroker(brokerSignUpDto);

        // then
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo("broker1");
        assertThat(response.email()).isEqualTo("broker@test.com");

        // 실제 DB에서 확인
        assertThat(brokerMapper.findById("broker1")).isPresent();
    }

    @Test
    @DisplayName("Member 로그인 요청")
    void loginMember_success() {
        // Given: 회원가입된 회원으로 로그인 요청
        authService.signUpMember(memberSignUpDto);
        RequestLoginDto loginDto = new RequestLoginDto(memberSignUpDto.id(), memberSignUpDto.password());

        // When: 로그인 요청
        ResponseLoginDto response = authService.login(loginDto);

        // Then: 로그인 성공
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(memberSignUpDto.id());
        assertThat(response.email()).isEqualTo(memberSignUpDto.email());
        assertThat(response.userType()).isEqualTo("MEMBER");
    }

    @Test
    @DisplayName("Broker 로그인 요청")
    void loginBroker_success() {
        // Given: 회원가입된 브로커로 로그인 요청
        authService.signUpBroker(brokerSignUpDto);
        RequestLoginDto loginDto = new RequestLoginDto(brokerSignUpDto.id(), brokerSignUpDto.password());

        // When: 로그인 요청
        ResponseLoginDto response = authService.login(loginDto);

        // Then: 로그인 성공
        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(brokerSignUpDto.id());
        assertThat(response.email()).isEqualTo(brokerSignUpDto.email());
        assertThat(response.userType()).isEqualTo("BROKER");
    }

    @Test
    @DisplayName("잘못된 비밀번호 로그인")
    void login_fail_wrongPassword() {
        // Given: 회원가입된 회원으로 잘못된 비밀번호로 로그인 요청
        authService.signUpMember(memberSignUpDto);
        RequestLoginDto loginDto = new RequestLoginDto(memberSignUpDto.id(), "wrong_password");

        // When & Then: 로그인 실패 예외 발생
        assertThatThrownBy(() -> authService.login(loginDto))
                .isInstanceOf(LoginFailedException.class);
    }

    @Test
    @DisplayName("존재하지 않는 사용자 로그인")
    void login_fail_nonExistentUser() {
        // Given: 존재하지 않는 사용자로 로그인 요청
        RequestLoginDto loginDto = new RequestLoginDto("nonexistent", "password123");

        // When & Then: 로그인 실패 예외 발생
        assertThatThrownBy(() -> authService.login(loginDto))
                .isInstanceOf(LoginFailedException.class);
    }

    @Test
    @DisplayName("회원가입_로그인_로그아웃_세션 비어있음 확인")
    void logout_success() {
        // Given: 회원가입 후 로그인하여 세션에 저장
        authService.signUpMember(memberSignUpDto);
        RequestLoginDto loginDto = new RequestLoginDto(memberSignUpDto.id(), memberSignUpDto.password());
        authService.login(loginDto);

        // When: 로그아웃 호출
        authService.logout();

        // Then: 세션이 비어있는지 확인
        assertThat(session.getAttribute(Session.MEMBER_ID.name())).isNull();
        assertThat(session.getAttribute(Session.TYPE.name())).isNull();
    }

}