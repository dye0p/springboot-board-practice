package com.springboot.board.springboot_board.application.auth;

import com.springboot.board.springboot_board.application.auth.business.*;
import com.springboot.board.springboot_board.application.auth.dto.Tokens;
import com.springboot.board.springboot_board.domain.member.Member;
import com.springboot.board.springboot_board.application.member.dto.request.MemberLoginRequest;
import com.springboot.board.springboot_board.domain.member.MemberRepository;
import com.springboot.board.springboot_board.global.exception.custom.MemberException;
import com.springboot.board.springboot_board.global.exception.errorcode.MemberErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TokenService {
    private static final int MILLISECONDS_IN_SECOND = 1000;

    private final TokenManager tokenManager;
    private final TokenRegistrar tokenRegistrar;
    private final BlackListRegistrar blackListRegistrar;
    private final TokenExpirationManager tokenExpirationManager;
    private final TokenResolver tokenResolver;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public Tokens login(MemberLoginRequest memberLoginRequest) {
        Member member = findMemberBy(memberLoginRequest);
        return saveToken(member);
    }

    public void logout(HttpServletRequest request) {
        String accessToken = tokenResolver.resolveToken(request);
        saveBlackList(accessToken);
    }

    private Member findMemberBy(MemberLoginRequest memberLoginRequest) {
        Member member = memberRepository.findByLoginId(memberLoginRequest.loginId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.INVALID_CREDENTIALS));

        if (!member.ischeckPassword(memberLoginRequest.password(), passwordEncoder)) {
            throw new MemberException(MemberErrorCode.INVALID_CREDENTIALS);
        }
        return member;
    }

    private void saveBlackList(String accessToken) {
        Long expriration = tokenExpirationManager.getExpriration(accessToken);
        blackListRegistrar.registrarBlackListToken(accessToken, expriration / MILLISECONDS_IN_SECOND);
    }

    private Tokens saveToken(Member member) {
        Tokens tokens = tokenManager.issueToken(member);
        tokenRegistrar.registrarToken(member, tokens.refreshToken());
        return tokens;
    }
}
