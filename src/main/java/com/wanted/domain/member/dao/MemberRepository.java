package com.wanted.domain.member.dao;

import com.wanted.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

  /**
   * 계정 아이디로 회원 찾기
   *
   * @return Optional 회원
   */
  Optional<Member> findByAccount(String account);

  /**
   * 회원 ID로 회원 + 회원 위치 패치 조인
   *
   * @param memberId 회원 id
   * @return 회원 위치를 포함한 회원
   */
  @Query("SELECT m "
      + "FROM Member m "
      + "LEFT JOIN FETCH m.memberLocation "
      + "WHERE m.id =:memberId")
  Optional<Member> findWithMemberLocationById(@Param("memberId") Long memberId);

}
