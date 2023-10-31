package com.wanted.domain.restaurant.openapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * RestaurantOpenApiResponse:맛집 open api responseDto
 */
@Getter
public class RestaurantOpenApiResponse {
    //시군명
    @JsonProperty("SIGUN_NM")
    private String sigunName;

    //위도
    @JsonProperty("REFINE_WGS84_LAT")
    private String lat;

    //경도
    @JsonProperty("REFINE_WGS84_LOGT")
    private String lon;

    //시군코드
    @JsonProperty("SIGUN_CD")
    private String sigun_code;

    //총 종업원
    @JsonProperty("TOT_EMPLY_CNT")
    private int totalNumber;

    //남성 종업원
    @JsonProperty("MALE_ENFLPSN_CNT")
    private int maleNumber;

    //여성 종업원
    @JsonProperty("FEMALE_ENFLPSN_CNT")
    private int femaleNumber;

    // 등급 구분명
    @JsonProperty("GRAD_DIV_NM")
    private String restaurantRatingName;

    // 급수 시설 구분명
    @JsonProperty("GRAD_FACLT_DIV_NM")
    private String restaurantWaterFacility;

    // 다중 이용 여부
    @JsonProperty("MULTI_USE_BIZESTBL_YN")
    private String restaurantAvailable;

    // 총 시설 규모
    @JsonProperty("TOT_FACLT_SCALE")
    private String restaurantFacilityScale;

    // 만들어진 년도
    @JsonProperty("YY")
    private String restaurantMadeYear;

    // 폐업 일자
    @JsonProperty("CLSBIZ_DE")
    private String restaurantCloseYear;

    //위생업종명
    @JsonProperty("SANITTN_INDUTYPE_NM")
    private String industryName;

    //위생업태명
    @JsonProperty("SANITTN_BIZCOND_NM")
    private String businessName;

    //소재지 도로명 주소
    @JsonProperty("REFINE_ROADNM_ADDR")
    private String roadNameAddress;

    //소재지 지번 주소
    @JsonProperty("REFINE_LOTNO_ADDR")
    private String lotNumberAddress;

    //소재지 우편번호
    @JsonProperty("REFINE_ZIP_CD")
    private String zip_code;

    //소재지 면적
    @JsonProperty("LOCPLC_AR")
    private long area;


    //사업장 명
    @JsonProperty("BIZPLC_NM")
    private String workplaceName;

    //사업가 인허가 일자
    @JsonProperty("LICENSG_DE")
    private String licenseDate;

    //사업자 상태명
    @JsonProperty("BSN_STATE_NM")
    private String businessStatus;

    //영업장 주변 구분명
    @JsonProperty("BSNSITE_CIRCUMFR_DIV_NM")
    private String classificationName;
}
