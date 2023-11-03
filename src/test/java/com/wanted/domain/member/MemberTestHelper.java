package com.wanted.domain.member;

import com.wanted.domain.member.entity.Member;

/**
 * 회원에 관한 테스트 헬퍼
 */
public class MemberTestHelper {

  public static Member createMemberWithId() {
    return Member.builder()
        .id(1L)
        .account("test1234")
        .password("test123*")
        .build();
  }

  public static Member createMember() {
    return Member.builder()
        .account("test1234")
        .password("test123*")
        .build();
  }
}