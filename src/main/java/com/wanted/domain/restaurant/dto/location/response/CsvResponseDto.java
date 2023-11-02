package com.wanted.domain.restaurant.dto.location.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
public class CsvResponseDto {

  private String lat;
  private String lon;
  private String doSi;
  private String siGunGu;
}
