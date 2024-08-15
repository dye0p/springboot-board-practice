package com.springboot.board.springboot_board.global.config;

import com.springboot.board.springboot_board.application.jwt.TokenResolver;
import com.springboot.board.springboot_board.application.jwt.TokenProvider;
import com.springboot.board.springboot_board.global.auth.jwt.exception.JwtAuthenticationEntryPoint;
import com.springboot.board.springboot_board.global.auth.jwt.filter.JwtAuthenticationFilter;
import com.springboot.board.springboot_board.global.auth.jwt.filter.JwtExceptionFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String[] PERMIT_LIST = {"/", "/h2-console/**", "/api/v1/join", "/api/v2/login",
            "/api/v1/check-email", "/api/v1/check-loginid", "/api/v2/auth/auth-code"};

    private final TokenProvider tokenProvider;
    private final TokenResolver tokenResolver;
    private final JwtAuthenticationEntryPoint entryPoint;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(PERMIT_LIST);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        http
                .authorizeHttpRequests((auth) ->
                        auth.anyRequest().authenticated());

        http
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider, tokenResolver), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionFilter(), JwtAuthenticationFilter.class)
                .sessionManagement((session) -> session.
                        sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(handler -> handler.authenticationEntryPoint(entryPoint));

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
