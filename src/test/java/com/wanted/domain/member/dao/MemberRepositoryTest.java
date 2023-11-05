package com.wanted.domain.member.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.wanted.domain.member.MemberTestHelper;
import com.wanted.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTest {

  @Autowired
  MemberRepository memberRepository;

  @Nested
  @DisplayName("회원 저장 관련 DB 테스트")
  class CreateMemberTest {

    @Test
    @DisplayName("회원이 정상적으로 저장됨")
    void 회원이_정상적으로_저장됨() {
      Member memberWithId = MemberTestHelper.createMemberWithId();

      Member savedMember = memberRepository.save(memberWithId);

      assertThat(memberWithId.getAccount()).isEqualTo(savedMember.getAccount());
    }
  }

  @Nested
  @DisplayName("회원 찾기 관련 DB 테스트")
  class ReadMemberTest {

    @Test
    @DisplayName("회원 account로 회원을 찾을 수 있어야 함")
    void 회원_account로_회원을_찾을_수_있어야_함() {
      Member member = MemberTestHelper.createMember();
      memberRepository.save(member);

      Member foundMember = memberRepository.findByAccount(member.getAccount()).orElseThrow();

      assertThat(foundMember.getAccount()).isEqualTo(member.getAccount());
    }
  }
}