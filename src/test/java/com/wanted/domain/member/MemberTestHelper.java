package com.wanted.domain.member;

import com.wanted.domain.member.entity.Authority;
import com.wanted.domain.member.entity.Member;
import com.wanted.domain.member.entity.location.MemberLocation;

/**
 * 회원에 관한 테스트 헬퍼
 */
public class MemberTestHelper {

  public static Member createMemberWithId() {
    return Member.builder()
        .id(1L)
        .account("test1234")
        .password("test123*")
        .authority(Authority.ROLE_USER)
        .build();
  }

  public static Member createMember() {
    return Member.builder()
        .account("test1234")
        .password("test123*")
        .authority(Authority.ROLE_USER)
        .build();
  }

  public static MemberLocation createMemberLocation() {
    return MemberLocation.builder()
        .lat(1232.111)
        .logt(212.1232)
        .build();
  }
}
