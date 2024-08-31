package com.springboot.board.springboot_board.application.member;

import com.springboot.board.springboot_board.application.jwt.TokenResolver;
import com.springboot.board.springboot_board.application.jwt.TokenService;
import com.springboot.board.springboot_board.application.jwt.dto.Tokens;
import com.springboot.board.springboot_board.application.member.dto.request.MemberLoginRequest;
import com.springboot.board.springboot_board.domain.member.Member;
import com.springboot.board.springboot_board.global.exception.custom.MemberException;
import com.springboot.board.springboot_board.global.exception.errorcode.MemberErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoginManager {

    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final MemberFinder memberFinder;
    private final TokenResolver tokenResolver;

    public Tokens login(final MemberLoginRequest memberLoginRequest) {
        Member member = memberFinder.findMemberBy(memberLoginRequest);
        isCheckedPassword(memberLoginRequest, member);

        return tokenService.saveToken(member);
    }

    private void isCheckedPassword(MemberLoginRequest memberLoginRequest, Member member) {
        if (!member.ischeckPassword(memberLoginRequest.password(), passwordEncoder)) {
            throw new MemberException(MemberErrorCode.INVALID_CREDENTIALS);
        }
    }

    public void logout(final HttpServletRequest request) {
        String accessToken = tokenResolver.resolveToken(request);
        tokenService.saveBlackList(accessToken);
    }
}
