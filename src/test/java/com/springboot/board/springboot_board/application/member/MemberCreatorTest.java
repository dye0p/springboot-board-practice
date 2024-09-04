package com.springboot.board.springboot_board.application.member;

import static org.assertj.core.api.Assertions.assertThat;

import com.springboot.board.springboot_board.IntegrationTestSupport;
import com.springboot.board.springboot_board.application.member.dto.request.MemberSaveRequest;
import com.springboot.board.springboot_board.domain.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MemberCreatorTest extends IntegrationTestSupport {

    @Autowired
    private MemberCreator memberCreator;

    @DisplayName("member를 생성하고 저장할 수 있다.")
    @Test
    void creatMember() {
        //given
        final MemberSaveRequest memberRequest = MemberSaveRequest.builder()
                .loginId("tset1234")
                .password("password1234!")
                .nickname("user")
                .email("test@test.com")
                .phone("010-1234-5678")
                .build();

        //when
        Member member = memberCreator.creatMember(memberRequest);

        //then
        assertThat(member).isNotNull();
    }
}