package com.springboot.board.springboot_board.domain.member;

import com.springboot.board.springboot_board.application.jwt.dto.TokenPayload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @DisplayName("encode된 password를 가진 Member를 생성한다.")
    @Test
    void create() {
        //given
        final String rawPassword = "password";

        //when
        final Member member = Member.create("loginId", rawPassword, "nickname",
                "email", "phone", passwordEncoder);

        //then
        assertThat(passwordEncoder.matches(rawPassword, member.getPassword())).isTrue();
    }

    @DisplayName("입력한 비밀번호가 encode된 비밀번호와 일치하는지 검증할 수 있다")
    @Test
    void isMatchesPassword() {
        //given
        final String rawPassword = "password";
        final Member member = Member.create("loginId", rawPassword, "nickname",
                "email", "phone", passwordEncoder);

        //when
        final boolean checkPassword = member.ischeckPassword(rawPassword, passwordEncoder);

        //then
        assertThat(checkPassword).isTrue();
    }

    @DisplayName("입력한 비밀번호가 encode된 비밀번호와 틀린지 검증할 수 있다")
    @Test
    void isNotMatchesPassword() {
        //given
        final String rawPassword = "password";
        final Member member = Member.create("loginId", rawPassword, "nickname",
                "email", "phone", passwordEncoder);

        //when
        final boolean checkPassword = member.ischeckPassword("differentPassword", passwordEncoder);

        //then
        assertThat(checkPassword).isFalse();
    }

    @DisplayName("Member를 생성하면 ROLE_USER 권한으로 생성된다.")
    @Test
    void MemberRole() {
        //given //when
        final Member member = createMemmber();

        //then
        assertThat(member.getRole().getValue()).isEqualTo("ROLE_USER");
    }

    @DisplayName("Member의 loginId, role 필드로 TokenPayload를 생성할 수 있다.")
    @Test
    void createTokenPayload() {
        //given
        final Member member = createMemmber();

        //when
        TokenPayload tokenPayload = member.createTokenPayload();

        //then
        assertThat(tokenPayload).isNotNull();
        assertThat(tokenPayload.memberId()).isEqualTo("loginId");
        assertThat(tokenPayload.role()).isEqualTo("ROLE_USER");
    }

    private Member createMemmber() {
        return Member.create("loginId", "password", "nickname",
                "email", "phone", passwordEncoder);
    }
}
