package com.wanted.domain.restaurant.dto.restaurant.response;

import com.wanted.domain.restaurant.constant.FoodType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestaurantDetailResDto {

  private Long id;

  @Enumerated(EnumType.STRING)
  private FoodType type;

  //RestaurantEmployee
  //총 종업원 수
  private Integer totalNumber;

  //남자 종업원 수
  private Integer maleNumber;

  //여자 종업원 수
  private Integer femaleNumber;

  //RestaurantFacility
  //등급 구분명
  private String ratingName;
  //급수 시설 구분명
  private String WaterFacilityName;

  // 다중 이용 여부
  private String restaurantAvailable;

  // 총 시설 규모
  private Long facilityScale;

  // 만들어진 년도
  private String madeYear;

  // 폐업 일자
  private LocalDate closeDate;

  //RestaurantHygiene
  // 위생 업종명
  private String industryName;

  // 위생 업태명
  private String businessName;

  //RestaurantLocation
  // 위도
  private Double lat;

  // 경도
  private Double lon;

  // 시군명
  private String sigunName;

  // 시군코드
  private Integer sigunCode;

  //RestaurantSite
  // 소재지 도로명 주소
  private String roadNameAddress;

  // 소재지 지번 주소
  private String lotNumberAddress;

  // 소재지 우편번호
  private Integer zipCode;

  // 소재지 면적
  private Long area;

  //RestaurantWorkplace
  // 사업장명
  private String workplaceName;

  // 사업자 인허가 일자
  private LocalDate licenseDate;

  // 사업자 상태명
  private String businessStatus;

  // 영업장 주변 구분명
  private String classificationName;
}
