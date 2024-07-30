package com.springboot.board.springboot_board.domain.member.service;

import com.springboot.board.springboot_board.domain.jwt.dto.Tokens;
import com.springboot.board.springboot_board.domain.jwt.service.TokenService;
import com.springboot.board.springboot_board.domain.member.domain.Member;
import com.springboot.board.springboot_board.domain.member.dto.MemberLoginRequest;
import com.springboot.board.springboot_board.domain.member.dto.MemberSaveRequest;
import com.springboot.board.springboot_board.domain.member.dto.MemberSaveResponse;
import com.springboot.board.springboot_board.domain.member.repository.MemberRepository;
import com.springboot.board.springboot_board.global.exception.custom.MemberException;
import com.springboot.board.springboot_board.global.exception.errorcode.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Transactional(readOnly = true)
    public void checkEmailDuplicate(String email) {
        if (memberRepository.existsByEmail(email))
            throw new MemberException(MemberErrorCode.EMAIL_DUPLICATION);
    }

    @Transactional(readOnly = true)
    public void checkLonginIdDuplicate(String loginId) {
        if (memberRepository.existsByLoginId(loginId))
            throw new MemberException(MemberErrorCode.LOGINID_DUPLICATION);
    }

    @Transactional
    public MemberSaveResponse join(MemberSaveRequest memberSaveRequest) {
        Member member = memberSaveRequest.toEntity();
        member.encodePassword(passwordEncoder);
        memberRepository.save(member);

        return MemberSaveResponse.ofMember(member);
    }

    @Transactional
    public Tokens login(MemberLoginRequest memberLoginRequest) {
        Member member = memberRepository.findByLoginId(memberLoginRequest.loginId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.INVALID_CREDENTIALS));

        if (!member.ischeckPassword(memberLoginRequest.password(), passwordEncoder)) {
            throw new MemberException(MemberErrorCode.INVALID_CREDENTIALS);
        }
        return tokenService.saveToken(member);
    }

    public void logout(String accessToken) {
        tokenService.saveBlackList(accessToken);
    }
}

