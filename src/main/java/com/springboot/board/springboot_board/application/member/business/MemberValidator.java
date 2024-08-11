package com.springboot.board.springboot_board.application.member.business;


import com.springboot.board.springboot_board.domain.member.MemberRepository;
import com.springboot.board.springboot_board.global.exception.custom.MemberException;
import com.springboot.board.springboot_board.global.exception.errorcode.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberRepository memberRepository;

    public void validateDuplicatedToEmail(String email) {
        if (memberRepository.existsByEmail(email))
            throw new MemberException(MemberErrorCode.EMAIL_DUPLICATION);
    }

    public void validateDuplicatedToLoginId(final String loginId) {
        if (memberRepository.existsByLoginId(loginId))
            throw new MemberException(MemberErrorCode.LOGINID_DUPLICATION);
    }
}
