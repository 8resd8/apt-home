package com.ssafy.home.auth.repository;

import com.ssafy.home.auth.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
@ActiveProfiles("test") // test 설정 사용
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 30; i++) {
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());
            String mid = "member" + i;
            Member member = Member.builder()
                    .mid(mid)
                    .name("Jane Doe " + i)
                    .phoneNum("010-1234-567" + i)
                    .password("hashed_password_" + i)
                    .salt("random_salt_" + i)
                    .email("member" + i + "@test.com")
                    .createdAt(now)
                    .updatedAt(now)
                    .lastLogin(LocalDateTime.now())
                    .build();

            memberMapper.insertMember(
                    member.getMid(),
                    member.getPassword(),
                    member.getSalt(),
                    member.getEmail(),
                    member.getPhoneNum(),
                    member.getName()
            );
        }
    }

    @Test
    @DisplayName("ID찾기 성공")
    void findById() {
        Optional<Member> member = memberMapper.findById("member1");

        assertTrue(member.isPresent());

        Member member1 = member.get();
        assertThat(member1.getMid()).isEqualTo("member1");
        assertThat(member1.getPassword()).isEqualTo("hashed_password_1");
        assertThat(member1.getEmail()).isEqualTo("member1@test.com");
    }

    @Test
    @DisplayName("ID찾기 실패")
    void findByIdFail() {
        Optional<Member> member = memberMapper.findById("member0");

        assertFalse(member.isPresent());
    }

    @Test
    @DisplayName("이메일로 멤버 찾기 성공")
    void findByEmail_success() {
        Optional<Member> member = memberMapper.findByEmail("member1@test.com");

        assertTrue(member.isPresent());

        Member findMember = member.get();
        assertThat(findMember.getEmail()).isEqualTo("member1@test.com");
        assertThat(findMember.getMid()).isEqualTo("member1");
    }

    @Test
    @DisplayName("이메일로 멤버 찾기 실패")
    void findByEmail_fail() {
        Optional<Member> member = memberMapper.findByEmail("nonexistent@test.com");

        assertFalse(member.isPresent());
    }

    @Test
    @DisplayName("멤버 삭제 성공")
    void deleteById_success() {
        String memberId = "member1";

        int result = memberMapper.deleteById(memberId);

        assertThat(result).isEqualTo(1);
        assertThat(memberMapper.findById(memberId)).isNotPresent();
    }

    @Test
    @DisplayName("멤버 삭제 실패")
    void deleteById_fail() {
        String nonExistentId = "nonexistentMember";

        int result = memberMapper.deleteById(nonExistentId);

        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("마지막 로그인 시간 업데이트 성공")
    void updateLastLogin_success() {
        String memberId = "member1";
        memberMapper.updateLastLogin(memberId);

        Optional<Member> memberOptional = memberMapper.findById(memberId);

        assertThat(memberOptional).isPresent();
        Member member = memberOptional.get();
        assertThat(member.getLastLogin()).isNotNull();

        LocalDateTime now = LocalDateTime.now();
        assertThat(member.getLastLogin()).isAfter(now.minusMinutes(1));
    }
}