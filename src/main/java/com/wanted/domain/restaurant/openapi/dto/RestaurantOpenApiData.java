package com.wanted.domain.restaurant.openapi.dto;


import static com.wanted.domain.restaurant.constant.NullProcessingValue.DATE_DEFAULT;
import static com.wanted.domain.restaurant.constant.NullProcessingValue.DOUBLE_DEFAULT;
import static com.wanted.domain.restaurant.constant.NullProcessingValue.INTEGER_DEFAULT;
import static com.wanted.domain.restaurant.constant.NullProcessingValue.LONG_DEFAULT;
import static com.wanted.domain.restaurant.constant.NullProcessingValue.STRING_DEFAULT;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.Getter;

/**
 * RestaurantOpenApiResponse:맛집 open api responseDto
 *
 * @JsonProperty: json 속성명과 dto 속성명이 달라도 매핑이 되게끔 합니다.
 * @JsonSetter: 기존에는 json 속성값이 null이어도 default값을 덮어쓰나, 해당 어노테이션으로 json 속성이 null이면 default값을 유지합니다.
 */
@Getter
public class RestaurantOpenApiData {
    //시군명
    @JsonProperty("SIGUN_NM")
    @JsonSetter(nulls = Nulls.SKIP)
    private String sigunName = (String) STRING_DEFAULT.getValue();

    //위도
    @JsonProperty("REFINE_WGS84_LAT")
    @JsonSetter(nulls = Nulls.SKIP)
    private Double lat = (Double) DOUBLE_DEFAULT.getValue();

    //경도
    @JsonProperty("REFINE_WGS84_LOGT")
    @JsonSetter(nulls = Nulls.SKIP)
    private Double lon = (Double) DOUBLE_DEFAULT.getValue();

    //시군코드
    @JsonProperty("SIGUN_CD")
    @JsonSetter(nulls = Nulls.SKIP)
    private String sigun_code = (String) STRING_DEFAULT.getValue();

    //총 종업원
    @JsonProperty("TOT_EMPLY_CNT")
    @JsonSetter(nulls = Nulls.SKIP)
    private Integer totalNumber = (Integer) INTEGER_DEFAULT.getValue();

    //남성 종업원
    @JsonProperty("MALE_ENFLPSN_CNT")
    @JsonSetter(nulls = Nulls.SKIP)
    private Integer maleNumber = (Integer) INTEGER_DEFAULT.getValue();

    //여성 종업원
    @JsonProperty("FEMALE_ENFLPSN_CNT")
    @JsonSetter(nulls = Nulls.SKIP)
    private Integer femaleNumber = (Integer) INTEGER_DEFAULT.getValue();

    // 등급 구분명
    @JsonProperty("GRAD_DIV_NM")
    @JsonSetter(nulls = Nulls.SKIP)
    private String ratingName = (String) STRING_DEFAULT.getValue();

    // 급수 시설 구분명
    @JsonProperty("GRAD_FACLT_DIV_NM")
    @JsonSetter(nulls = Nulls.SKIP)
    private String waterFacilityName = (String) STRING_DEFAULT.getValue();

    // 다중 이용 여부
    @JsonProperty("MULTI_USE_BIZESTBL_YN")
    @JsonSetter(nulls = Nulls.SKIP)
    private String restaurantAvailable = (String) STRING_DEFAULT.getValue();

    // 총 시설 규모
    @JsonProperty("TOT_FACLT_SCALE")
    @JsonSetter(nulls = Nulls.SKIP)
    private Long facilityScale = (Long) LONG_DEFAULT.getValue();

    // 만들어진 년도
    @JsonProperty("YY")
    @JsonSetter(nulls = Nulls.SKIP)
    private String madeYear = (String) STRING_DEFAULT.getValue();

    // 폐업 일자
    @JsonProperty("CLSBIZ_DE")
    @JsonSetter(nulls = Nulls.SKIP)
    private String closeDate = (String) DATE_DEFAULT.getValue();

    //위생업종명
    @JsonProperty("SANITTN_INDUTYPE_NM")
    @JsonSetter(nulls = Nulls.SKIP)
    private String industryName = (String) STRING_DEFAULT.getValue();

    //위생업태명
    @JsonProperty("SANITTN_BIZCOND_NM")
    @JsonSetter(nulls = Nulls.SKIP)
    private String businessName = (String) STRING_DEFAULT.getValue();

    //소재지 도로명 주소
    @JsonProperty("REFINE_ROADNM_ADDR")
    @JsonSetter(nulls = Nulls.SKIP)
    private String roadNameAddress = (String) STRING_DEFAULT.getValue();

    //소재지 지번 주소
    @JsonProperty("REFINE_LOTNO_ADDR")
    @JsonSetter(nulls = Nulls.SKIP)
    private String lotNumberAddress = (String) STRING_DEFAULT.getValue();

    //소재지 우편번호
    @JsonProperty("REFINE_ZIP_CD")
    @JsonSetter(nulls = Nulls.SKIP)
    private Integer zipCode = (Integer) INTEGER_DEFAULT.getValue();

    //소재지 면적
    @JsonProperty("LOCPLC_AR")
    @JsonSetter(nulls = Nulls.SKIP)
    private Long area = (Long) LONG_DEFAULT.getValue();

    //사업장 명
    @JsonProperty("BIZPLC_NM")
    @JsonSetter(nulls = Nulls.SKIP)
    private String workplaceName = (String) STRING_DEFAULT.getValue();

    //사업가 인허가 일자
    @JsonProperty("LICENSG_DE")
    @JsonSetter(nulls = Nulls.SKIP)
    private String licenseDate = (String) DATE_DEFAULT.getValue();

    //사업자 상태명
    @JsonProperty("BSN_STATE_NM")
    @JsonSetter(nulls = Nulls.SKIP)
    private String businessStatus = (String) STRING_DEFAULT.getValue();

    //영업장 주변 구분명
    @JsonProperty("BSNSITE_CIRCUMFR_DIV_NM")
    @JsonSetter(nulls = Nulls.SKIP)
    private String classificationName = (String) STRING_DEFAULT.getValue();
}
