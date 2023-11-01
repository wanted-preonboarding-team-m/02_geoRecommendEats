package com.wanted.domain.restaurant.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 맛집의 기타 시설 정보를 담는 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RestaurantFacility {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "restaurant_facility_id")
  private Long id;

  // 등급 구분명
  @Column(nullable = false)
  private String ratingName;

  // 급수 시설 구분명
  @Column(nullable = false)
  private String WaterFacilityName;

  // 다중 이용 여부
  @Column(nullable = false)
  private String restaurantAvailable;

  // 총 시설 규모
  @Column(nullable = false)
  private Long facilityScale;

  // 만들어진 년도
  @Column(nullable = false)
  private String madeYear;

  // 폐업 일자
  @Column(nullable = false)
  private LocalDate closeDate;

  // Restaurnt 테이블이 부모 테이블
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_id")
  private Restaurant restaurant;

  @Builder
  private RestaurantFacility(String ratingName, String waterFacilityName, String restaurantAvailable,
      Long facilityScale, String madeYear, LocalDate closeDate, Restaurant restaurant) {
    this.ratingName = ratingName;
    WaterFacilityName = waterFacilityName;
    this.restaurantAvailable = restaurantAvailable;
    this.facilityScale = facilityScale;
    this.madeYear = madeYear;
    this.closeDate = closeDate;
    this.restaurant = restaurant;
  }
}
