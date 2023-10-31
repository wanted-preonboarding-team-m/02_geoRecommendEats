package com.wanted.domain.restaurant.openapi.dto.location.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * LocationResponseDto : 맛집 위치
 * @lat : REFINE_WGS84_LAT
 * @lon : REFINE_WGS84_LOGT
 * @sigun_name : SIGUN_NM
 * @sigun_code : SIGUN_CD
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class LocationResponseDto {
    //위도
    private String lat;
    //경도
    private String lon;
    //시군명
    private String sigun_name;
    //시군코드
    private String sigun_code;
}
