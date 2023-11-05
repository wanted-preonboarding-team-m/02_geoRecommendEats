package com.wanted.domain.auth.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.wanted.domain.auth.application.AuthService;
import com.wanted.domain.member.dto.request.MemberLoginReqDto;
import com.wanted.domain.member.dto.request.MemberSignUpReqDto;
import com.wanted.global.config.security.data.TokenReqDto;
import com.wanted.global.util.format.response.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  /**
   * 회원 가입
   *
   * @param reqDto 회원 가입 입력 데이터
   * @return 201, 생성된 회원의 ID
   */
  @PostMapping("/signup")
  public ResponseEntity<ApiResponse> signUp(
      @Valid @RequestBody MemberSignUpReqDto reqDto) {
    reqDto.validPasswordConfirm(); // 비밀번호 확인 검증

    Long memberId = authService.signup(reqDto);
    return ResponseEntity.status(CREATED)
        .body(ApiResponse.toSuccessForm(memberId));
  }

  /**
   * 아이디 중복 확인
   *
   * @param account 확인할 아이디
   * @return 204
   */
  @GetMapping("/signup/exists/account")
  public ResponseEntity<ApiResponse> checkDuplicateAccount(
      @RequestParam @NotNull String account) {
    authService.checkDuplicateAccount(account); // 아이디 중복 확인

    return ResponseEntity.status(NO_CONTENT)
        .body(ApiResponse.toSuccessForm(""));
  }

  /**
   * 로그인 및 토큰 발급
   *
   * @param reqDto 로그인 입력 데이터
   * @return 200, JWT 토큰
   */
  @PostMapping("login")
  public ResponseEntity<ApiResponse> login(
      @RequestBody MemberLoginReqDto reqDto
  ) {
    return ResponseEntity.ok(ApiResponse.toSuccessForm(authService.login(reqDto)));
  }

  /**
   * 토큰 재발급
   *
   * @param reqDto 토큰 정보 dto
   * @return 200, 재발급 된 JWT 토큰
   */
  @PostMapping("/reissue")
  public ResponseEntity<ApiResponse> reissue(
      @RequestBody TokenReqDto reqDto
  ) {
    return ResponseEntity.ok(ApiResponse.toSuccessForm(authService.reissue(reqDto)));
  }

  /**
   * 로그아웃 및 토큰 삭제
   *
   * @param reqDto 토큰 정보
   * @return 200, 토큰 삭제
   */
  @PostMapping("logout")
  public ResponseEntity<ApiResponse> logout(
      @RequestBody TokenReqDto reqDto
  ) {
    authService.logout(reqDto);
    return ResponseEntity.ok(ApiResponse.toSuccessForm(null));
  }
}
