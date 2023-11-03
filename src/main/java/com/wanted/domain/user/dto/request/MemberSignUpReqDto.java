package com.wanted.domain.user.dto.request;

import static com.wanted.domain.user.entity.Member.MAX_ACCOUNT_LENGTH;

import com.wanted.domain.user.entity.Member;
import com.wanted.global.config.error.BusinessException;
import com.wanted.global.config.error.ErrorCode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 회원가입할 때 입력받는 req DTO
 */
@Getter
@NoArgsConstructor
public class MemberSignUpReqDto {

  public static final int MIN_ACCOUNT_LENGTH = 6;
  /**
   * 대,소문자 + 특수문자로 구성된 8~16 자리인 정규식
   */
  public static final String PASSWORD_REGEX_PATTERN = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$";

  // 계정명
  @NotNull(message = "계정명을 입력해주세요.")
  @Length(min = MIN_ACCOUNT_LENGTH, max = MAX_ACCOUNT_LENGTH,
      message = "계정명을 {min} ~ {max} 사이로 입력해주세요.")
  private String account;

  // 비밀번호
  @NotNull(message = "비밀번호를 입력해주세요.")
  @Pattern(regexp = PASSWORD_REGEX_PATTERN, message = "비밀번호는 특수문자를 포함한 8~16자리 수 여야만 합니다.")
  private String password;

  // 비밀번호 확인
  @NotNull(message = "비밀번호 확인을 입력해주세요.")
  @Pattern(regexp = PASSWORD_REGEX_PATTERN, message = "비밀번호는 특수문자를 포함한 8~16자리 수 여야만 합니다.")
  private String passwordConfirm;

  /**
   * 비밀번호 확인이 비밀번호와 같은지 확인
   *
   * @return 다르면 예외 처리
   */
  public void validPasswordConfirm() {
    if (!password.equals(passwordConfirm)) {
      throw new BusinessException(null, "passwordConfirm", ErrorCode.MEMBER_WRONG_PASSWORD_CONFIRM);
    }
  }

  /**
   * Dto 객체를 Member Entity로 변환
   *
   * @param passwordEncoder 비밀번호 인코더
   * @return Member Entity
   */
  public Member toEntity(PasswordEncoder passwordEncoder) {
    return Member.builder()
        .account(this.account)
        .password(passwordEncoder.encode(this.password))
        .build();
  }
}
