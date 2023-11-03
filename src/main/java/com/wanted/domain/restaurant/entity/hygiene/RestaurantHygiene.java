package com.wanted.domain.restaurant.entity.hygiene;

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
 * 맛집의 위생 정보를 담는 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RestaurantHygiene {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "restaurant_hygiene_id")
  private Long id;

  // 위생 업종명
  @Column(nullable = false)
  private String industryName;

  // 위생 업태명
  @Column(nullable = false)
  private String businessName;

  @Builder
  private RestaurantHygiene(String industryName, String businessName) {
    this.industryName = industryName;
    this.businessName = businessName;
  }
}
