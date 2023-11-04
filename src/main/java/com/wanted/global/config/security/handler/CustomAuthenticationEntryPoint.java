package com.wanted.global.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.global.error.ErrorCode;
import com.wanted.global.util.format.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 유효한 자격증명을 제공하지 않고 접근하려 할때 예외 핸들러
 */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper mapper;

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException
  ) throws IOException {

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json; charset=UTF-8");

    ResponseEntity<ApiResponse> errorResponse =
        ResponseEntity
            .status(ErrorCode.ACCESS_AUTH_ENTRY_EXCEPTION.getHttpStatus())
            .body(ApiResponse.toErrorForm(ErrorCode.ACCESS_AUTH_ENTRY_EXCEPTION.getMessage()));

    response.getWriter().write(mapper.writeValueAsString(errorResponse));
  }
}