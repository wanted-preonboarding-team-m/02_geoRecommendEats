package com.wanted.domain.restaurant.dto.location.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * CsvResponseDto : csv에서 받아오는 dto
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
public class CsvResponseDto {

  //도,시
  private String doSi;
  //시군구
  private String siGunGu;
  //위도
  private String lat;
  //경도
  private String lon;

}
