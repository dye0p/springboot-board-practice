package com.springboot.board.springboot_board.application.member;

import com.springboot.board.springboot_board.application.member.dto.request.MemberSaveRequest;
import com.springboot.board.springboot_board.application.member.dto.response.MemberSaveResponse;
import com.springboot.board.springboot_board.domain.member.Member;
import com.springboot.board.springboot_board.domain.member.MemberRepository;
import com.springboot.board.springboot_board.domain.member.Role;
import com.springboot.board.springboot_board.global.exception.custom.MemberException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
    }

    @DisplayName("회원을 생성하여 저장할 수 있다.")
    @Test
    void join() {
        //given
        MemberSaveRequest member = MemberSaveRequest.builder()
                .loginId("id")
                .password("password")
                .nickname("nickname")
                .email("email")
                .phone("123")
                .build();

        //when
        MemberSaveResponse join = memberService.join(member);

        //then
        assertThat(join).extracting("id", "nickname", "email", "role")
                .containsExactly(1L, "nickname", "email", "ROLE_USER");
    }

    @DisplayName("중복된 email로 회원가입을 시도할 경우 예외를 던진다")
    @Test
    void joinWithDuplicatedEmail() {
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

        MemberSaveRequest joinMember = MemberSaveRequest.builder()
                .email(email)
                .build();

        //when //then
        assertThatThrownBy(() -> memberService.join(joinMember))
                .isInstanceOf(MemberException.class)
                .hasMessage("이미 가입된 이메일 입니다.");
    }

    @DisplayName("입력한 email에 대하여 중복검사를 했을때 중복된 email 이라면 예외를 던진다.")
    @Test
    void checkEmailDuplicate() {
        //given
        String newEmail = "email";

        Member member = Member.builder()
                .loginId("id")
                .password("password")
                .nickname("nickname")
                .email("email")
                .phone("123")
                .role(Role.USER)
                .build();

        memberRepository.save(member);

        //when //then
        assertThatThrownBy(() -> memberService.checkEmailDuplicate(newEmail))
                .isInstanceOf(MemberException.class)
                .hasMessage("이미 가입된 이메일 입니다.");
    }

    @DisplayName("입력한 loginId 에 대하여 중복검사를 했을때 중복된 loginId 라면 예외를 던진다.")
    @Test
    void checkLonginIdDuplicate() {
        //given
        String newLoginId = "loginId";

        Member member = Member.builder()
                .loginId("loginId")
                .password("password")
                .nickname("nickname")
                .email("email")
                .phone("123")
                .role(Role.USER)
                .build();

        memberRepository.save(member);

        //when //then
        assertThatThrownBy(() -> memberService.checkLonginIdDuplicate(newLoginId))
                .isInstanceOf(MemberException.class)
                .hasMessage("이미 가입된 아이디 입니다.");
    }
}