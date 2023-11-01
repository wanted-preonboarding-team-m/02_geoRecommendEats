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
 * 맛집의 사업장 정보를 담는 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RestaurantWorkplace {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "restaurant_workplace_id")
  private Long id;

  // 사업장명
  @Column(nullable = false)
  private String workplaceName;

  // 사업자 인허가 일자
  @Column(nullable = false)
  private LocalDate licenseDate;

  // 사업자 상태명
  @Column(nullable = false)
  private String businessStatus;

  // 영업장 주변 구분명
  @Column(nullable = false)
  private String classificationName;

  // Restaurnt 테이블이 부모 테이블
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "restaurant_id")
  private Restaurant restaurant;

  @Builder
  private RestaurantWorkplace(String workplaceName, LocalDate licenseDate, String businessStatus,
      String classificationName, Restaurant restaurant) {
    this.workplaceName = workplaceName;
    this.licenseDate = licenseDate;
    this.businessStatus = businessStatus;
    this.classificationName = classificationName;
    this.restaurant = restaurant;
  }
}
