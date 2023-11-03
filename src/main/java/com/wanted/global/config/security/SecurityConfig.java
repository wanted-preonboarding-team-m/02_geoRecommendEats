package com.wanted.global.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 시큐리티에 대한 전반적인 설정
 */
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  /**
   * 비밀번호 암호화 방식을 `BCrypt`로 설정
   *
   * @return BCrypt 빈 등록
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * 시큐리티 필터 설정
   *
   * @param http http 시큐리티
   * @return 커스텀 http 시큐리티
   * @throws Exception 모든 예외
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    return http
        .httpBasic(AbstractHttpConfigurer::disable) // Http basic Auth 기반 로그인 인증창 사용 X
        .csrf(AbstractHttpConfigurer::disable) // Rest api 이므로, csrf 보안 사용 X
        .cors(AbstractHttpConfigurer::disable) // cors 보안 사용 X
        .formLogin(AbstractHttpConfigurer::disable) // 로그인 폼 화면 사용 X

        // 시큐리티가 기본적인 세션을 사용하지 않게 변경
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        // HTTP 요청에 대한 인가 설정
        // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
        .authorizeHttpRequests(request ->
            request.requestMatchers(
                    "/auth/**"
                )
                .permitAll())
        // TODO 특정 도메인 인증 추가 필요 (임시로 모든 도메인을 인증 필요로 설정)
        .authorizeHttpRequests(request ->
            request.anyRequest().authenticated()
        )
        
        .build();
  }

}