package com.wanted.domain.restaurant.dto.employee.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * EmployeeResponseDto : 맛집 종업원 수
 * @total_number : TOT_EMPLY_CNT
 * @MALE_ENFLPSN_CNT : male_number
 * @FEMALE_ENFLPSN_CNT : female_number
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class EmployeeResponseDto {
    //총 종업원
    private int totalNumber;
    //남성 종업원
    private int male_number;
    //여성 종업원
    private int female_number;
}
