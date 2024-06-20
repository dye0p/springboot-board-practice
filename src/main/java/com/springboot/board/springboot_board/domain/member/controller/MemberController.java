package com.springboot.board.springboot_board.domain.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping("/test")
    public String test() {
        return "테스트";
    }
}
