package com.wanted.domain.restaurant.entity.type;

import com.wanted.domain.restaurant.constant.FoodType;
import com.wanted.domain.restaurant.entity.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
 * 맛집의 타입 정보를 담는 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RestaurantType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "restaurant_type_id")
  private Long id;

  // 맛집 타입 (중식, 일식, 패스트푸드)
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private FoodType type;

  @Builder
  private RestaurantType(FoodType type) {
    this.type = type;
  }
}
