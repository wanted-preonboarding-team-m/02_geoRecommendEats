package com.wanted.domain.member.application;

import com.wanted.domain.member.dao.MemberRepository;
import com.wanted.domain.member.dto.response.MemberInfoResDto;
import com.wanted.domain.member.entity.Member;
import com.wanted.global.config.security.TokenProvider;
import com.wanted.global.error.BusinessException;
import com.wanted.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

  private final MemberRepository memberRepository;
  private final TokenProvider tokenProvider;

  /**
   * 유저에 관한 정보를 줌
   *
   * @param memberId 유저 Id
   * @return 유저 정보 dto
   */
  public MemberInfoResDto readMemberInfo(
      Long memberId
  ) {
    Member member = memberRepository.findById(memberId).orElseThrow(
        () -> new BusinessException(memberId, "memberId", ErrorCode.MEMBER_NOT_FOUND)
    );

    MemberInfoResDto resDto = new MemberInfoResDto(member);
    return resDto;
  }
}
