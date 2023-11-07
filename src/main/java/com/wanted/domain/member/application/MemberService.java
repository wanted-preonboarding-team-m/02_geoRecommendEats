package com.wanted.domain.member.application;

import com.wanted.domain.member.dao.MemberRepository;
import com.wanted.domain.member.dao.location.MemberLocationRepository;
import com.wanted.domain.member.dto.request.MemberLocationUpdateReqDto;
import com.wanted.domain.member.dto.response.MemberInfoResDto;
import com.wanted.domain.member.entity.Member;
import com.wanted.domain.member.entity.location.MemberLocation;
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
  private final MemberLocationRepository memberLocationRepository;
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
    Member member = getMemberWithLocationById(memberId);

    MemberInfoResDto resDto = new MemberInfoResDto(member);
    return resDto;
  }

  /**
   * id로 회원 + 위치 찾기
   *
   * @param memberId 회원 ID
   * @return 찾은 회원
   */
  private Member getMemberWithLocationById(Long memberId) {
    Member member = memberRepository.findWithMemberLocationById(memberId).orElseThrow(
        () -> new BusinessException(memberId, "memberId", ErrorCode.MEMBER_NOT_FOUND)
    );
    return member;
  }

  /**
   * 유저 위치를 생성 또는 업데이트
   *
   * @param token    JWT 토큰
   * @param memberId 회원 id
   * @param reqDto   위치 정보 데이터
   * @return 위치 ID
   */
  @Transactional
  public Long updateLocation(
      String token,
      Long memberId,
      MemberLocationUpdateReqDto reqDto
  ) {

    // id로 회원+회원 위치 찾기, 회원이 없으면 예외처리
    Member member = getMemberWithLocationById(memberId);

    // 토큰 검증
    checkAuth(token, member.getAccount());

    // dto -> entity
    MemberLocation memberLocation = reqDto.toEntity();

    // 처음 위치를 저장 하면, 컬럼 저장.
    if (member.getMemberLocation() == null) {
      memberLocationRepository.save(memberLocation);
      member.updateLocation(memberLocation);

      return memberLocation.getId();
    }

    // 처음이 아니면, 업데이트.
    member.getMemberLocation().update(memberLocation);
    return member.getMemberLocation().getId();
  }

  /**
   * 접근 권한이 있는지 확인
   * 토큰의 account 명과 접근하려는 회원의 account 명이 같은지 확인
   *
   * @param token   JWT 토큰
   * @param account 확인할 id
   */
  private void checkAuth(String token, String account) {
    if (!tokenProvider.getAccountFromToken(token).equals(account)) {
      throw new BusinessException(account, "account", ErrorCode.ACCESS_DENIED_EXCEPTION);
    }
  }
}
