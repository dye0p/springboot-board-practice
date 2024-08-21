package com.springboot.board.springboot_board.application.member;

import com.springboot.board.springboot_board.IntegrationTestSupport;
import com.springboot.board.springboot_board.domain.member.Member;
import com.springboot.board.springboot_board.domain.member.MemberRepository;
import com.springboot.board.springboot_board.domain.member.Role;
import com.springboot.board.springboot_board.global.exception.custom.MemberException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberValidatorTest extends IntegrationTestSupport {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberValidator memberValidator;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAllInBatch();
    }

    @DisplayName("중복된 email이 존재할 때 예외를 던진다.")
    @Test
    void validateDuplicatedToEmail() {
        //given
        Member member = Member.builder()
                .loginId("logindId")
                .password("password")
                .nickname("nickname")
                .email("email")
                .role(Role.USER)
                .phone("phone")
                .build();

        memberRepository.save(member);

        //when //then
        assertThatThrownBy(() -> memberValidator.validateDuplicatedToEmail("email"))
                .isInstanceOf(MemberException.class)
                .hasMessage("이미 가입된 이메일 입니다.");
    }


    @DisplayName("중복된 loginId가 존재할 때 예외를 던진다.")
    @Test
    void validateDuplicatedToLoginId() {
        //given
        Member member = Member.builder()
                .loginId("logindId")
                .password("password")
                .nickname("nickname")
                .email("email")
                .role(Role.USER)
                .phone("phone")
                .build();

        memberRepository.save(member);

        //when //then
        assertThatThrownBy(() -> memberValidator.validateDuplicatedToLoginId("logindId"))
                .isInstanceOf(MemberException.class)
                .hasMessage("이미 가입된 아이디 입니다.");
    }
}