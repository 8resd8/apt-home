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

import java.time.LocalDateTime;
import java.util.Optional;

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
            String id = "member" + i;
            Member member = Member.builder()
                    .id(id)
                    .name("Jane Doe " + i)
                    .phoneNum("010-1234-567" + i)
                    .password("hashed_password_" + i)
                    .salt("random_salt_" + i)
                    .email("member" + i + "@test.com")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .lastLogin(LocalDateTime.now())
                    .build();

            memberMapper.insertMember(
                    member.getId(),
                    member.getPassword(),
                    member.getSalt(),
                    member.getEmail(),
                    member.getPhoneNum(),
                    member.getName(),
                    member.getCreatedAt(),
                    member.getUpdatedAt()
            );
        }
    }

    @Test
    @DisplayName("ID찾기 성공")
    void findById() {
        Optional<Member> member = memberMapper.findById("member1");

        assertTrue(member.isPresent());

        Member member1 = member.get();
        Assertions.assertThat(member1.getId()).isEqualTo("member1");
        Assertions.assertThat(member1.getPassword()).isEqualTo("hashed_password_1");
        Assertions.assertThat(member1.getEmail()).isEqualTo("member1@test.com");
    }

    @Test
    @DisplayName("ID찾기 실패")
    void findByIdFail() {
        Optional<Member> member = memberMapper.findById("member0");

        assertFalse(member.isPresent());
    }
}