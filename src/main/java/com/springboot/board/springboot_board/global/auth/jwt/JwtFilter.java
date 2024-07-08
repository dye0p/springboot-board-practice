package com.springboot.board.springboot_board.global.auth.jwt;

import com.springboot.board.springboot_board.domain.common.Role;
import com.springboot.board.springboot_board.domain.member.domain.Member;
import com.springboot.board.springboot_board.global.config.security.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("token null");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.split(" ")[1];

        if (tokenProvider.isExpired(token)) {
            System.out.println("token expired");
            filterChain.doFilter(request, response);
            return;
        }

        String loginId = tokenProvider.getLogidId(token);
        Role role = Role.fromValue(tokenProvider.getRole(token));

        Member member = Member.builder()
                .loginId(loginId)
                .role(role)
                .build();

        CustomUserDetails memberDetails = new CustomUserDetails(member);
        Authentication authToken = new UsernamePasswordAuthenticationToken(memberDetails, null,
                memberDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
