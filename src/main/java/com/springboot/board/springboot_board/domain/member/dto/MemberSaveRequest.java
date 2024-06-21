package com.springboot.board.springboot_board.domain.member.dto;

import com.springboot.board.springboot_board.domain.Role;
import com.springboot.board.springboot_board.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record MemberSaveRequest(
        String loginId,
        String password,
        String nickname,
        String email,
        String phone,
        Role role
) {

    public Member toEntity() {
        Member member = Member.builder().build();
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .nickname(nickname)
                .email(email)
                .phone(phone)
                .role(Role.USER)
                .build();
    }
}
