package com.wanted.global.config.security.data;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenReqDto {

  private String accessToken;
  private String refreshToken;
}