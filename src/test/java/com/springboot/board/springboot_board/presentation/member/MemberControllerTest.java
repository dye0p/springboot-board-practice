package com.springboot.board.springboot_board.presentation.member;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.springboot.board.springboot_board.ControllerTestSupport;
import com.springboot.board.springboot_board.application.member.dto.request.MemberSaveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;


@WithMockUser("user1")
class MemberControllerTest extends ControllerTestSupport {

    @DisplayName("회원가입을 한다.")
    @Test
    void joinMember() throws Exception {
        //given
        final MemberSaveRequest member = MemberSaveRequest.builder()
                .loginId("tset1234")
                .password("password1234!")
                .nickname("user")
                .email("test@test.com")
                .phone("010-1234-5678")
                .build();

        //when //then
        mockMvc.perform(
                        post("/api/v1/join")
                                .content(objectMapper.writeValueAsBytes(member))
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value("true"))
                .andExpect(jsonPath("$.code").value("201"))
                .andExpect(jsonPath("$.message").value("ok"));
    }

    @DisplayName("사용가능한 email인지 확인할 수 있다")
    @Test
    void checkEmailDuplicate() throws Exception {
        //given
        final String email = "test@test.com";

        //when //then
        mockMvc.perform(
                        get("/api/v1/check-email")
                                .param("email", email)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"))
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("사용 가능한 이메일 입니다"));
    }

    @DisplayName("사용가능한 loginId인지 확인할 수 있다")
    @Test
    void checkLoginIdDuplicate() throws Exception {
        //given
        final String loginId = "testid";

        //when //then
        mockMvc.perform(
                        get("/api/v1/check-loginid")
                                .param("loginId", loginId)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"))
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("사용 가능한 아이디 입니다"));
    }
}