package com.wanted.domain.restaurant.openapi.dto.hygiene.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * HygieneResponseDto : 맛집 위생
 * @industry_name : SANITTN_INDUTYPE_NM
 * @business_name : SANITTN_BIZCOND_NM
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class HygieneResponseDto {
    //위생업종명
    private String industry_name;
    //위생업태명
    private String business_name;
}
