package com.springboot.board.springboot_board.domain.jwt.controller;

import com.springboot.board.springboot_board.domain.common.response.ApiResponse;
import com.springboot.board.springboot_board.domain.common.response.SuccessResponse;
import com.springboot.board.springboot_board.domain.jwt.dto.Tokens;
import com.springboot.board.springboot_board.domain.jwt.service.TokenService;
import com.springboot.board.springboot_board.domain.member.dto.request.MemberLoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/v2/login")
    public ResponseEntity<ApiResponse<Tokens>> doLogin(@RequestBody @Valid MemberLoginRequest memberLoginRequest) {
        Tokens token = tokenService.login(memberLoginRequest);
        return ResponseEntity.ok(SuccessResponse.of(token));
    }

    @GetMapping("/v1/logout")
    public ResponseEntity<ApiResponse<Void>> doLogout(HttpServletRequest request) {
        tokenService.logout(request);
        return ResponseEntity.ok(SuccessResponse.ok());
    }
}
