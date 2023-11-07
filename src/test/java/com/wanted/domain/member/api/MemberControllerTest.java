package com.wanted.domain.member.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.config.restdocs.AbstractRestDocsTests;
import com.wanted.domain.member.MemberTestHelper;
import com.wanted.domain.member.application.MemberService;
import com.wanted.domain.member.dto.request.MemberLocationUpdateReqDto;
import com.wanted.domain.member.dto.response.MemberInfoResDto;
import com.wanted.domain.member.dto.response.MemberLocationResDto;
import com.wanted.domain.member.entity.Member;
import com.wanted.global.error.BusinessException;
import com.wanted.global.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest extends AbstractRestDocsTests {

  private static final String MEMBER_URL = "/api/v1/member";

  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private MemberService memberService;

  Member member = MemberTestHelper.createMemberWithId();

  @Nested
  @DisplayName("사용자 조회 관련 컨트롤러 테스트")
  class readMember {

    @Test
    @DisplayName("토큰이 올바를 때, 사용자 정보 반환 기능이 정상적으로 작동한다.")
    void 토큰이_올바를_때_사용자_정보_반환_기능이_정상적으로_작동한다() throws Exception {
      MemberInfoResDto resDto =
          MemberInfoResDto.builder()
              .account(member.getAccount())
              .memberLocation(new MemberLocationResDto(MemberTestHelper.createMemberLocation()))
              .authority(member.getAuthority().toString())
              .build();

      given(memberService.readMemberInfo(1L)).willReturn(resDto);

      mockMvc.perform(get(MEMBER_URL + "/1")
              .header(HttpHeaders.AUTHORIZATION, "JWT_TOKEN"))
          .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("토큰이 올바르지 않을 때, 사용자 정보 반환 기능이 예외가 발생한다.")
    void 토큰이_올바르지_않을_때_사용자_정보_반환_기능이_예외가_발생한다() throws Exception {

      given(memberService.readMemberInfo(1L)).willThrow(
          new BusinessException("", "", ErrorCode.ACCESS_AUTH_ENTRY_EXCEPTION)
      );

      mockMvc.perform(get(MEMBER_URL + "/1"))
          .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
  }

  @Nested
  @DisplayName("사용자 수정 관련 컨트롤러 테스트")
  class updateMember {

    @Test
    @DisplayName("접근 권한이 있을 때, 사용자 위치 정보 수정이 정상적으로 작동된다.")
    @WithMockUser
    void 접근_권한이_있을_때_사용자_위치_정보_수정이_정상적으로_작동된다() throws Exception {
      MemberLocationUpdateReqDto reqDto =
          MemberLocationUpdateReqDto.builder()
              .lat(112.121)
              .logt(121.121)
              .build();

      given(memberService.updateLocation(any(), any(), any())).willReturn(1L);

      mockMvc.perform(put(MEMBER_URL + "/location/1")
              .header(HttpHeaders.AUTHORIZATION, "JWT_TOKEN")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(reqDto)))
          .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("접근 권한이 없을 때, 사용자 위치 정보 수정이 예외가 발생한다.")
    void 접근_권한이_없을_때_사용자_위치_정보_수정이_예외가_발생한다() throws Exception {
      MemberLocationUpdateReqDto reqDto =
          MemberLocationUpdateReqDto.builder()
              .lat(112.121)
              .logt(121.121)
              .build();

      given(memberService.updateLocation(any(), any(), any())).willThrow(
          new BusinessException(1, "memberId", ErrorCode.ACCESS_DENIED_EXCEPTION)
      );

      mockMvc.perform(put(MEMBER_URL + "/location/1")
              .header(HttpHeaders.AUTHORIZATION, "JWT_TOKEN")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(reqDto)))
          .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
  }
}