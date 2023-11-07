package com.wanted.global.config.security.utils;

import com.wanted.global.error.BusinessException;
import com.wanted.global.error.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 시큐리티 관련 유틸
 */
public class SecurityUtil {

  private SecurityUtil() {
  }

  /**
   * SecurityContext 에 유저 정보가 저장되는 시점
   * Request 가 들어올 때 JwtFilter 의 doFilter 에서 저장
   *
   * @return
   */
  public static Long getCurrentMemberId() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || authentication.getName() == null) {
      throw new BusinessException(null, "springContext", ErrorCode.SPRING_CONTEXT_NOT_FOUND);
    }

    return Long.parseLong(authentication.getName());
  }
}