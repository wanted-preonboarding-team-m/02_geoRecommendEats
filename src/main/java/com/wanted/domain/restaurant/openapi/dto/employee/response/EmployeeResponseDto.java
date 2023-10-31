package com.wanted.domain.restaurant.openapi.dto.employee.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * EmployeeResponseDto : 맛집 종업원 수
 * @total_number : TOT_FACLT_SCALE
 * @MALE_ENFLPSN_CNT : male_number
 * @FEMALE_ENFLPSN_CNT : female_number
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponseDto {
    //총 종업원
    private int total_number;
    //남성 종업원
    private int male_number;
    //여성 종업원
    private int female_number;
}
