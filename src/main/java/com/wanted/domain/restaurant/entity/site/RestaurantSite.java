package com.wanted.domain.restaurant.entity.site;

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
 * 맛집의 소재지 정보를 담는 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RestaurantSite {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "restaurant_site_id")
  private Long id;

  // 소재지 도로명 주소
  @Column(nullable = false)
  private String roadNameAddress;

  // 소재지 지번 주소
  @Column(nullable = false)
  private String lotNumberAddress;

  // 소재지 우편번호
  @Column(nullable = false)
  private Integer zipCode;

  // 소재지 면적
  @Column(nullable = false)
  private Long area;

  @Builder
  private RestaurantSite(String roadNameAddress, String lotNumberAddress, Integer zipCode, Long area) {
    this.roadNameAddress = roadNameAddress;
    this.lotNumberAddress = lotNumberAddress;
    this.zipCode = zipCode;
    this.area = area;
  }
}
