package com.wanted.domain.member.api;

import com.wanted.domain.member.application.MemberService;
import com.wanted.domain.member.dto.response.MemberInfoResDto;
import com.wanted.global.util.format.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  /**
   * 유저에 관한 정보 읽기
   *
   * @param memberId 유저 아이디
   * @return 비밀번호를 제외한 유저의 정보
   */
  @GetMapping("/{memberId}")
  public ResponseEntity<ApiResponse> readUserInfo(
      @PathVariable("memberId") Long memberId
  ) {
    MemberInfoResDto resDto = memberService.readMemberInfo(memberId);

    return ResponseEntity.ok(ApiResponse.toSuccessForm(resDto));
  }
}
