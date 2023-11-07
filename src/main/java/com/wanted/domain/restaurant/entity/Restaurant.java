package com.wanted.domain.restaurant.entity;

import com.wanted.domain.restaurant.entity.employee.RestaurantEmployee;
import com.wanted.domain.restaurant.entity.facility.RestaurantFacility;
import com.wanted.domain.restaurant.entity.hygiene.RestaurantHygiene;
import com.wanted.domain.restaurant.entity.location.RestaurantLocation;
import com.wanted.domain.restaurant.entity.site.RestaurantSite;
import com.wanted.domain.restaurant.entity.type.RestaurantType;
import com.wanted.domain.restaurant.entity.workplace.RestaurantWorkplace;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 맛집 정보를 담는 엔티티 세부 속성들은 별개의 테이블과 관계를 맺어서 관리한다.
 */
@Getter
@NoArgsConstructor
@Entity
public class Restaurant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "restaurant_id")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_employee_id")
  private RestaurantEmployee restaurantEmployee;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_facility_id")
  private RestaurantFacility restaurantFacility;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_hygiene_id")
  private RestaurantHygiene restaurantHygiene;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_location_id")
  private RestaurantLocation restaurantLocation;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_site_id")
  private RestaurantSite restaurantSite;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_type_id")
  private RestaurantType restaurantType;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_workplace_id")
  private RestaurantWorkplace restaurantWorkplace;

  @Column(nullable = false)
  private double rate;

  @Builder
  private Restaurant(RestaurantEmployee restaurantEmployee, RestaurantFacility restaurantFacility,
      RestaurantHygiene restaurantHygiene, RestaurantLocation restaurantLocation,
      RestaurantSite restaurantSite, RestaurantType restaurantType,
      RestaurantWorkplace restaurantWorkplace) {
    this.restaurantEmployee = restaurantEmployee;
    this.restaurantFacility = restaurantFacility;
    this.restaurantHygiene = restaurantHygiene;
    this.restaurantLocation = restaurantLocation;
    this.restaurantSite = restaurantSite;
    this.restaurantType = restaurantType;
    this.restaurantWorkplace = restaurantWorkplace;
  }
}
