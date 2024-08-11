package com.springboot.board.springboot_board.application.member.dto.response;

import com.springboot.board.springboot_board.domain.member.Member;
import lombok.Builder;

@Builder
public record MemberSaveResponse(
        Long id,
        String nickname,
        String email,
        String role
) {

    public static MemberSaveResponse of(Member member) {
        return MemberSaveResponse.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .role(member.getRole().getValue())
                .build();
    }
}
