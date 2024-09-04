package com.springboot.board.springboot_board.presentation.auth;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.springboot.board.springboot_board.ControllerTestSupport;
import com.springboot.board.springboot_board.application.auth.dto.MailSendRequest;
import com.springboot.board.springboot_board.application.auth.dto.MailVerifyRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;


@WithMockUser("user1")
class AuthControllerTest extends ControllerTestSupport {

    @DisplayName("인증코드를 전송한다.")
    @Test
    void sendEmail() throws Exception {
        //given
        MailSendRequest mailSendRequest = new MailSendRequest("test@test.com");

        //when //then
        mockMvc.perform(
                        post("/api/v2/auth/auth-code")
                                .content(objectMapper.writeValueAsBytes(mailSendRequest))
                                .contentType(APPLICATION_JSON)
                                .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"))
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("인증코드가 전송되었습니다"));

    }

    @DisplayName("인증코드를 검증한다.")
    @Test
    void verifyMailAuthCode() throws Exception {
        //given
        MailVerifyRequest mailVerifyRequest = new MailVerifyRequest("12345678", "test@test.com");

        //when //then
        mockMvc.perform(
                        get("/api/v2/auth/auth-code")
                                .content(objectMapper.writeValueAsBytes(mailVerifyRequest))
                                .contentType(APPLICATION_JSON)
                                .with(csrf())

                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"))
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("인증되었습니다"));
    }
}