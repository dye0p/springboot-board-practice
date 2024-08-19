package com.springboot.board.springboot_board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.board.springboot_board.application.member.MemberService;
import com.springboot.board.springboot_board.presentation.member.MemberController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = MemberController.class)
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected MemberService memberService;
}
