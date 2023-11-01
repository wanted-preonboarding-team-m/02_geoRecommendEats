package com.wanted.domain.restaurant.entity;

import com.wanted.domain.restaurant.constant.FoodType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  // Restaurnt 테이블이 부모 테이블
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "restaurant_id")
  private Restaurant restaurant;

  @Builder
  private RestaurantType(FoodType type, Restaurant restaurant) {
    this.type = type;
    this.restaurant = restaurant;
  }
}
