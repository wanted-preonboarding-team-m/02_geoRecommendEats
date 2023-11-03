package com.wanted.domain.auth.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.wanted.domain.member.MemberTestHelper;
import com.wanted.domain.member.dao.MemberRepository;
import com.wanted.domain.member.dto.request.MemberSignUpReqDto;
import com.wanted.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

  @InjectMocks
  private AuthService authService;
  @Mock
  private MemberRepository memberRepository;
  @Mock
  private PasswordEncoder passwordEncoder;

  @Nested
  @DisplayName("회원 가입 관련 서비스 테스트")
  class Signup {

    Member member = MemberTestHelper.createMemberWithId();

    @Test
    @DisplayName("회원 가입이 정상적으로 되어, ID를 반환해야한다.")
    void 회원_가입이_정상적으로_되어_ID를_반환해야한다() {
      MemberSignUpReqDto memberSignUpReqDto =
          MemberSignUpReqDto.builder()
              .account(member.getAccount())
              .password(member.getPassword())
              .passwordConfirm(member.getPassword())
              .build();

      given(memberRepository.save(any())).willReturn(member);
      given(passwordEncoder.encode(any())).willReturn("test123*");

      Long createdMemberId = authService.signup(memberSignUpReqDto);

      assertThat(1L).isEqualTo(createdMemberId);
    }

    @Test
    @DisplayName("이미 아이디가 존재하면 예외 발생.")
    void 이미_아이디가_존재하면_예외_발생() {
      given(memberRepository.findByAccount(member.getAccount())).willReturn(any());

      assertThatThrownBy(() ->
          authService.checkDuplicateAccount(member.getAccount())
      );
    }
  }
}