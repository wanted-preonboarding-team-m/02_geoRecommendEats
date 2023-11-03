package com.wanted.domain.auth.application;

import com.wanted.domain.user.dao.MemberRepository;
import com.wanted.domain.user.dto.request.MemberSignUpReqDto;
import com.wanted.domain.user.entity.Member;
import com.wanted.global.config.error.BusinessException;
import com.wanted.global.config.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

  private final MemberRepository memberRepository;
  // 시큐리티 설정에서 등록한 Bcrypt 인코더
  private final PasswordEncoder passwordEncoder;

  /**
   * 회원 가입
   *
   * @param reqDto 회원가입 입력 데이터
   * @return 생성된 회원의 id
   */
  @Transactional
  public Long signup(MemberSignUpReqDto reqDto) {
    // 아이디 중복 서버에서 다시 확인
    checkDuplicateAccount(reqDto.getAccount());

    Member member = reqDto.toEntity(passwordEncoder);
    // 저장
    Member createdMember = memberRepository.save(member);

    return createdMember.getId();
  }

  /**
   * 회원 아이디 중복 확인
   *
   * @param account 확인할 회원 아이디
   */
  public void checkDuplicateAccount(String account) {
    if (memberRepository.findByAccount(account).isPresent()) {
      throw new BusinessException(account, "account", ErrorCode.MEMBER_ACCOUNT_DUPLICATE);
    }
  }
}
