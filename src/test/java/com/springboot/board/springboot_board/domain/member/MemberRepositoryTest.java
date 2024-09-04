package com.springboot.board.springboot_board.domain.member;

import static org.assertj.core.api.Assertions.assertThat;

import com.springboot.board.springboot_board.IntegrationTestSupport;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MemberRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
    }

    @DisplayName("중복된 eamil이 있는 경우 true를 반환한다.")
    @Test
    void existsByEmail() {
        //given
        String email = "email";

        Member member = Member.builder()
                .loginId("id")
                .password("password")
                .nickname("nickname")
                .email(email)
                .phone("123")
                .role(Role.USER)
                .build();

        memberRepository.save(member);

        //when
        boolean result = memberRepository.existsByEmail(email);

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("중복된 eamil이 없는 경우 false를 반환한다.")
    @Test
    void notExistsByEmail() {
        //given
        String email = "email";

        //when
        boolean result = memberRepository.existsByEmail(email);

        //then
        assertThat(result).isFalse();
    }

    @DisplayName("중복된 loginId가 있는 경우 true를 반환한다.")
    @Test
    void existsByLoginId() {
        //given
        String logindId = "id";

        Member member = Member.builder()
                .loginId(logindId)
                .password("password")
                .nickname("nickname")
                .email("email")
                .phone("123")
                .role(Role.USER)
                .build();

        memberRepository.save(member);

        //when
        boolean result = memberRepository.existsByLoginId(logindId);

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("중복된 loginId가 없는 경우 false를 반환한다.")
    @Test
    void notExistsByLoginId() {
        //given
        String logindId = "id";

        //when
        boolean result = memberRepository.existsByLoginId(logindId);

        //then
        assertThat(result).isFalse();
    }

    @DisplayName("loginId로 일치하는 member를 찾을 수 있다")
    @Test
    void findByLoginId() {
        //given
        String loginId = "id";

        Member member = Member.builder()
                .loginId(loginId)
                .password("password")
                .nickname("nickname")
                .email("email")
                .phone("123")
                .role(Role.USER)
                .build();

        memberRepository.save(member);

        //when
        Optional<Member> result = memberRepository.findByLoginId(loginId);

        //then
        assertThat(result).isPresent();
        assertThat(result.get().getLoginId()).isEqualTo(loginId);
    }

    @DisplayName("loginId로 일치하는 member가 없을때 Optional.empty를 반환한다")
    @Test
    void findByLoginIdWithEmpty() {
        //given
        String loginId = "id";

        //when
        Optional<Member> result = memberRepository.findByLoginId(loginId);

        //then
        assertThat(result).isEmpty();
    }
}