package com.springboot.board.springboot_board.domain.member.dto;

import com.springboot.board.springboot_board.domain.member.domain.Member;

public record MemberLoginResponse(
        String nikcname,
        String role
) {

    public static MemberLoginResponse ofMember(Member member) {
        return new MemberLoginResponse(
                member.getNickname(),
                member.getRole().getValue()
        );
    }
}
