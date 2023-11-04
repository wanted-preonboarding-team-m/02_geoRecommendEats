package com.wanted.domain.auth.api;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.config.restdocs.AbstractRestDocsTests;
import com.wanted.domain.auth.application.AuthService;
import com.wanted.domain.member.MemberTestHelper;
import com.wanted.domain.member.dto.request.MemberSignUpReqDto;
import com.wanted.domain.member.entity.Member;
import com.wanted.global.config.security.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
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
}