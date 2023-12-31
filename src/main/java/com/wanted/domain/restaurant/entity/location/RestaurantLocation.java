package com.wanted.domain.restaurant.entity.location;

import com.wanted.domain.restaurant.entity.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 맛집의 위치 정보를 담는 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RestaurantLocation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "restaurant_location_id")
  private Long id;

  // 위도
  @Column(nullable = false)
  private Double lat;

  // 경도
  @Column(nullable = false)
  private Double lon;

  // 시군명
  @Column(nullable = false)
  private String sigunName;

  // 시군코드
  @Column(nullable = false)
  private Integer sigunCode;

  @Builder
  private RestaurantLocation(Double lat, Double lon, String sigunName, Integer sigunCode) {
    this.lat = lat;
    this.lon = lon;
    this.sigunName = sigunName;
    this.sigunCode = sigunCode;
  }
}
