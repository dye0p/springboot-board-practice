package com.springboot.board.springboot_board.global.auth.jwt.filter;

import com.springboot.board.springboot_board.domain.common.Role;
import com.springboot.board.springboot_board.domain.jwt.business.TokenResolver;
import com.springboot.board.springboot_board.domain.member.domain.Member;
import com.springboot.board.springboot_board.global.auth.jwt.TokenProvider;
import com.springboot.board.springboot_board.global.config.security.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final TokenResolver tokenResolver;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String token = tokenResolver.resolveToken(request);

        if (token != null && tokenProvider.validateToken(token)) {
            Authentication authToken = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(String token) {
        String loginId = tokenProvider.getLogidId(token);
        Role role = Role.fromValue(tokenProvider.getRole(token));

        Member member = Member.builder()
                .loginId(loginId)
                .role(role)
                .build();

        CustomUserDetails memberDetails = new CustomUserDetails(member);

        return new UsernamePasswordAuthenticationToken(memberDetails, null,
                memberDetails.getAuthorities());
    }


}
