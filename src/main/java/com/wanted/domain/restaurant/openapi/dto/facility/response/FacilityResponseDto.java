package com.wanted.domain.restaurant.openapi.dto.facility.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * EmployeeResponseDto : 맛집 시설
 * @restaurant_rating_name : GRAD_DIV_NM
 * @restaurant_water_facility : GRAD_FACLT_DIV_NM
 * @restaurant_available : MULTI_USE_BIZESTBL_YN
 * @restaurant_facility_scale : TOT_FACLT_SCALE
 * @restaurant_made_year : YY
 * @restaurant_close_year : CLSBIZ_DE
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacilityResponseDto {
    // 등급 구분명
    private String restaurant_rating_name;
    // 급수 시설 구분명
    private String restaurant_water_facility;
    // 다중 이용 여부
    private String restaurant_available;
    // 총 시설 규모
    private String restaurant_facility_scale;
    // 만들어진 년도
    private String restaurant_made_year;
    // 폐업 일자
    private String restaurant_close_year;
}
