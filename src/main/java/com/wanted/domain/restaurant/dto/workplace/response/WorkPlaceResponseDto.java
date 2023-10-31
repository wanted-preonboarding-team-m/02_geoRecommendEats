package com.wanted.domain.restaurant.dto.workplace.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * WorkPlaceResponseDto : 맛집 사업장
 * @workplace_name : BIZPLC_NM
 * @license_date : LICENSG_DE
 * @business_status : BSN_STATE_NM
 * @classification_name : BSNSITE_CIRCUMFR_DIV_NM
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class WorkPlaceResponseDto {
    //사업장 명
    private String workplaceName;
    //사업가 인허가 일자
    private String licenseDate;
    //사업자 상태명
    private String businessStatus;
    //영업장 주변 구분명
    private String classificationName;

}
