package com.springboot.board.springboot_board.application.member;

import com.springboot.board.springboot_board.application.member.dto.request.MemberLoginRequest;
import com.springboot.board.springboot_board.domain.member.Member;
import com.springboot.board.springboot_board.domain.member.MemberRepository;
import com.springboot.board.springboot_board.global.exception.custom.MemberException;
import com.springboot.board.springboot_board.global.exception.errorcode.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberFinder {

    private final MemberRepository memberRepository;

    public Member findMemberBy(final MemberLoginRequest memberLoginRequest) {
        return memberRepository.findByLoginId(memberLoginRequest.loginId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.INVALID_CREDENTIALS));
    }
}
