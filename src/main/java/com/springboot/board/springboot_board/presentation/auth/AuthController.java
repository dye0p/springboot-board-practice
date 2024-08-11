package com.springboot.board.springboot_board.presentation.auth;

import com.springboot.board.springboot_board.global.response.ApiResponse;
import com.springboot.board.springboot_board.global.response.SuccessResponse;
import com.springboot.board.springboot_board.application.auth.dto.Tokens;
import com.springboot.board.springboot_board.application.auth.TokenService;
import com.springboot.board.springboot_board.application.member.dto.request.MemberLoginRequest;
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
