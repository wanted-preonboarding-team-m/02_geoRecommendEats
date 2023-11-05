//package com.wanted.global.config.security;
//
//import com.wanted.global.config.security.filter.JwtFilter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
///**
// * 직접 만든 TokenProvider 와 JwtFilter 를 적용
// */
//@RequiredArgsConstructor
//@Configuration
//public class JwtSecurityConfig {
//
//  private final TokenProvider tokenProvider;
//
//  /**
//   * TokenProvider 를 주입 받아 JwtFilter 를 통해 Security 로직에 필터를 등록
//   *
//   * @param http 시큐리티 http
//   */
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    JwtFilter customFilter = new JwtFilter(tokenProvider);
//    http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
//    return http.build();
//  }
//}