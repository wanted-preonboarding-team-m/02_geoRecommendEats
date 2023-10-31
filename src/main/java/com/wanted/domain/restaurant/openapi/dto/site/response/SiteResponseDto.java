package com.wanted.domain.restaurant.openapi.dto.site.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * SiteResponseDto : 맛집 소재지
 * @road_name_address : REFINE_ROADNM_ADDR
 * @REFINE_LOTNO_ADDR : REFINE_LOTNO_ADDR
 * @zip_code : REFINE_ZIP_CD
 * @area : LOCPLC_AR
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class SiteResponseDto {
    //소재지 도로명 주소
    private String road_name_address;
    //소재지 지번 주소
    private String lot_number_address;
    //소재지 우편번호
    private String zip_code;
    //소재지 면적
    private long area;
}
