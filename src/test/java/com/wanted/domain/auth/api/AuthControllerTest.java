package com.wanted.domain.auth.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.config.restdocs.AbstractRestDocsTests;
import com.wanted.domain.auth.application.AuthService;
import com.wanted.domain.member.MemberTestHelper;
import com.wanted.domain.member.dto.request.MemberLoginReqDto;
import com.wanted.domain.member.dto.request.MemberSignUpReqDto;
import com.wanted.domain.member.entity.Member;
import com.wanted.global.config.security.data.TokenDto;
import com.wanted.global.config.security.data.TokenReqDto;
import com.wanted.global.error.BusinessException;
import com.wanted.global.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@WebMvcTest(controllers = AuthController.class,
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebMvcConfigurer.class)})
class AuthControllerTest extends AbstractRestDocsTests {

  private static final String AUTH_URL = "/api/v1/auth";

  @Autowired
  private final ObjectMapper objectMapper = new ObjectMapper();

  @MockBean
  private AuthService authService;

  Member member = MemberTestHelper.createMemberWithId();

  @Nested
  @DisplayName("회원 가입 관련 컨트롤러 테스트")
  class signup {

    @Test
    @DisplayName("회원 가입이 정상적으로 성공한다.")
    void 회원_가입이_정상적으로_성공한다() throws Exception {
      MemberSignUpReqDto reqDto =
          MemberSignUpReqDto.builder()
              .account(member.getAccount())
              .password(member.getPassword())
              .passwordConfirm(member.getPassword())
              .build();

      given(authService.signup(reqDto)).willReturn(1L);

      mockMvc.perform(post(AUTH_URL + "/signup")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(reqDto)))
          .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("올바르지 않은 데이터는 회원 가입이 실패한다.")
    void 올바르지_않은_데이터는_회원_가입이_실패한다() throws Exception {
      MemberSignUpReqDto reqDto =
          MemberSignUpReqDto.builder()
              .account("1")
              .password(member.getPassword())
              .passwordConfirm(member.getPassword())
              .build();

      given(authService.signup(reqDto)).willReturn(1L);

      mockMvc.perform(post(AUTH_URL + "/signup")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(reqDto)))
          .andExpect(status().isBadRequest());
    }
  }

  @Nested
  @DisplayName("로그인 관련 테스트")
  class login {

    @Test
    @DisplayName("아이디 비번이 일치할 경우, 토큰이 반환이 성공된다.")
    void 아이디_비번이_일치할_경우_토큰이_반환이_성공된다_() throws Exception {
      MemberLoginReqDto reqDto =
          MemberLoginReqDto.builder()
              .account("test123")
              .password("test123*")
              .build();

      TokenDto tokenDto =
          TokenDto.builder()
              .accessToken("새로운 Access 토큰")
              .refreshToken("새로운 Refresh 토큰")
              .accessTokenExpiresIn(166574L)
              .grantType("올바른 Grant 타입")
              .build();

      given(authService.login((any()))).willReturn(tokenDto);

      mockMvc.perform(post(AUTH_URL + "/login")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(reqDto)))
          .andExpect(status().isOk());
    }

    @Test
    @DisplayName("아이디 비번이 일치하지 않을 경우, 토큰이 반환이 실패한다.")
    void 아이디_비번이_일치하지_않을_경우_토큰이_반환이_실패한다_() throws Exception {
      MemberLoginReqDto reqDto =
          MemberLoginReqDto.builder()
              .account("test123")
              .password("test123*")
              .build();

      given(authService.login((any()))).willThrow(
          new BusinessException(null, "password", ErrorCode.MEMBER_PASSWORD_BAD_REQUEST));

      mockMvc.perform(post(AUTH_URL + "/login")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(reqDto)))
          .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("올바른 토큰일 경우, 토큰 재발급에 성공한다.")
    void 올바른_토큰일_경우_토큰_재발급에_성공한다() throws Exception {
      TokenReqDto reqDto =
          TokenReqDto.builder()
              .accessToken("올바른 Access 토큰")
              .refreshToken("올바른 Refresh 토큰")
              .build();

      TokenDto tokenDto =
          TokenDto.builder()
              .accessToken("새로운 Access 토큰")
              .refreshToken("새로운 Refresh 토큰")
              .accessTokenExpiresIn(166574L)
              .grantType("올바른 Grant 타입")
              .build();

      given(authService.reissue(any())).willReturn(tokenDto);

      mockMvc.perform(post(AUTH_URL + "/reissue")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(reqDto)))
          .andExpect(status().isOk());
    }

    @Test
    @DisplayName("올바르지 않은 토큰일 경우, 토큰 재발급에 실패한다.")
    void 올바르지_않은_토큰일_경우_토큰_재발급에_실패한다() throws Exception {
      TokenReqDto reqDto =
          TokenReqDto.builder()
              .accessToken("올바르지 않은 Access 토큰")
              .refreshToken("올바르지 않은 Refresh 토큰")
              .build();

      given(authService.reissue(any())).willThrow(
          new BusinessException(null, "refreshToken", ErrorCode.REFRESH_TOKEN_MISMATCH)
      );

      mockMvc.perform(post(AUTH_URL + "/reissue")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(reqDto)))
          .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("올바른 토큰일 경우, 로그아웃에 성공한다.")
    void 올바른_토큰일_경우_로그아웃에_성공한다() throws Exception {
      TokenReqDto reqDto =
          TokenReqDto.builder()
              .accessToken("올바른 Access 토큰")
              .refreshToken("올바른 Refresh 토큰")
              .build();

      mockMvc.perform(post(AUTH_URL + "/logout")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(reqDto)))
          .andExpect(status().isOk());
    }
  }
}