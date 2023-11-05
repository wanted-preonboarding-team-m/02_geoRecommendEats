package com.wanted.domain.auth.application;

import com.wanted.domain.member.dao.MemberRepository;
import com.wanted.domain.member.entity.Member;
import com.wanted.global.error.BusinessException;
import com.wanted.global.error.ErrorCode;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AuthenticationManagerBuilder 의  authenticate 커스텀
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;

  /**
   * UserDetails 와 Authentication 의 패스워드 비교 + 검증
   *
   * @param username 회원의 아이디
   * @return 회원 Entity -> UserDetail 객체로 변환
   * @throws UsernameNotFoundException
   */
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = memberRepository.findByAccount(username).orElseThrow(
        () -> new BusinessException(username, "account", ErrorCode.MEMBER_ACCOUNT_NOT_FOUND)
    );

    UserDetails userDetails = createUserDetails(member);
    return userDetails;
  }

  /**
   * UserDetails 객체로 만들어서 리턴
   *
   * @param member
   * @return
   */
  private UserDetails createUserDetails(Member member) {
    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
        member.getAuthority().toString());

    return new User(
        String.valueOf(member.getId()),
        member.getPassword(),
        Collections.singleton(grantedAuthority)
    );
  }
}