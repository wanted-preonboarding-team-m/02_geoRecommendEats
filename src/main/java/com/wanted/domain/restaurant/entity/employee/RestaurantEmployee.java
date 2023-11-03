package com.wanted.domain.restaurant.entity.employee;

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
 * 맛집의 종업원 수를 담는 엔티티
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RestaurantEmployee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "restaurant_employee_id")
  private Long id;

  // 총 종업원
  @Column(nullable = false)
  private Integer totalNumber;

  // 남성 종업원
  @Column(nullable = false)
  private Integer maleNumber;

  // 여성 종업원
  @Column(nullable = false)
  private Integer femaleNumber;

  // Restaurnt 테이블이 부모 테이블
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_id")
  private Restaurant restaurant;

  @Builder
  private RestaurantEmployee(Integer totalNumber, Integer maleNumber, Integer femaleNumber,
      Restaurant restaurant) {
    this.totalNumber = totalNumber;
    this.maleNumber = maleNumber;
    this.femaleNumber = femaleNumber;
    this.restaurant = restaurant;
  }
}
