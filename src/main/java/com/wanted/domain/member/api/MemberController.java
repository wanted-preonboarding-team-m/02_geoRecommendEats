package com.wanted.domain.member.api;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.wanted.domain.member.application.MemberService;
import com.wanted.domain.member.dto.request.MemberLocationUpdateReqDto;
import com.wanted.domain.member.dto.response.MemberInfoResDto;
import com.wanted.global.util.format.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

  /**
   * 유저 위치 업데이트
   *
   * @param token    JWT 토큰
   * @param memberId 회원 ID
   * @param reqDto   유저 위치 정보
   * @return 업데이트된 유저 위치 ID
   */
  @PutMapping("/location/{memberId}")
  public ResponseEntity<ApiResponse> updateUserLocation(
      @RequestHeader(AUTHORIZATION) String token,
      @PathVariable("memberId") Long memberId,
      @RequestBody @Valid MemberLocationUpdateReqDto reqDto
  ) {
    Long locationId = memberService.updateLocation(token, memberId, reqDto);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.toSuccessForm(locationId));
  }
}
