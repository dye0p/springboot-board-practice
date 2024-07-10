package com.springboot.board.springboot_board.domain.member.service;

import com.springboot.board.springboot_board.domain.member.domain.Member;
import com.springboot.board.springboot_board.domain.member.dto.MemberLoginRequest;
import com.springboot.board.springboot_board.domain.member.dto.MemberSaveRequest;
import com.springboot.board.springboot_board.domain.member.dto.MemberSaveResponse;
import com.springboot.board.springboot_board.domain.member.repository.MemberRepository;
import com.springboot.board.springboot_board.global.auth.jwt.TokenProvider;
import com.springboot.board.springboot_board.global.auth.jwt.dto.JwtResponse;
import com.springboot.board.springboot_board.global.exception.CustomException;
import com.springboot.board.springboot_board.global.exception.errorcode.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Value("${jwt.token-validity-in-seconds}")
    private Long expiredTime;

    @Transactional(readOnly = true)
    public void checkEmailDuplicate(String email) {
        if (memberRepository.existsByEmail(email))
            throw new CustomException(MemberErrorCode.EMAIL_DUPLICATION);
    }

    @Transactional(readOnly = true)
    public void checkLonginIdDuplicate(String loginId) {
        if (memberRepository.existsByLoginId(loginId))
            throw new CustomException(MemberErrorCode.LOGINID_DUPLICATION);
    }

    @Transactional
    public MemberSaveResponse join(MemberSaveRequest memberSaveRequest) {
        Member member = memberSaveRequest.toEntity();
        member.encodePassword(passwordEncoder);
        memberRepository.save(member);

        return MemberSaveResponse.ofMember(member);
    }

    @Transactional
    public JwtResponse login(MemberLoginRequest memberLoginRequest) {

        Member member = memberRepository.findByLoginId(memberLoginRequest.loginId())
                .orElseThrow(() -> new CustomException(MemberErrorCode.INVALID_CREDENTIALS));

        if (!member.ischeckPassword(memberLoginRequest.password(), passwordEncoder)) {
            throw new CustomException(MemberErrorCode.INVALID_CREDENTIALS);
        }
        String accessToken = tokenProvider.createJwt(memberLoginRequest.loginId(), member.getRole().getValue(), expiredTime);

        return new JwtResponse(accessToken);
    }
}
