package com.wanted.domain.restaurant.entity;

import com.wanted.domain.restaurant.entity.employee.RestaurantEmployee;
import com.wanted.domain.restaurant.entity.facility.RestaurantFacility;
import com.wanted.domain.restaurant.entity.hygiene.RestaurantHygiene;
import com.wanted.domain.restaurant.entity.location.RestaurantLocation;
import com.wanted.domain.restaurant.entity.site.RestaurantSite;
import com.wanted.domain.restaurant.entity.type.RestaurantType;
import com.wanted.domain.restaurant.entity.workplace.RestaurantWorkplace;
import com.wanted.domain.review.entity.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
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

  // 리뷰들 (1:N)
  @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
  private List<Review> reviews = new ArrayList<>();

  @Builder
  private Restaurant(Long id, RestaurantEmployee restaurantEmployee,
      RestaurantFacility restaurantFacility,
      RestaurantHygiene restaurantHygiene, RestaurantLocation restaurantLocation,
      RestaurantSite restaurantSite, RestaurantType restaurantType,
      RestaurantWorkplace restaurantWorkplace) {
    this.id = id;
    this.restaurantEmployee = restaurantEmployee;
    this.restaurantFacility = restaurantFacility;
    this.restaurantHygiene = restaurantHygiene;
    this.restaurantLocation = restaurantLocation;
    this.restaurantSite = restaurantSite;
    this.restaurantType = restaurantType;
    this.restaurantWorkplace = restaurantWorkplace;
  }

  /**
   * 평점을 업데이트 한다. - 새로운 리뷰가 들어올 때
   * 총 평점 (원래 총 평점(원래 평점 * 원래 리뷰의 수) + 새로운 평점) / 총 리뷰 수 (원래 리뷰의 수 + 1)
   *
   * @param score 새로 들어온 평점
   */
  public void updateRateByWrite(Double score) {
    this.rate = (this.rate * this.reviews.size() + score) / (this.reviews.size() + 1);
  }

  /**
   * 평점을 업데이트 한다. - 이전 리뷰의 수정으로 인해
   * 총 평점 (원래 총 평점 (원래 평점 * 원래 리뷰의 수 ) - 이전 평점 + 새로운 평점 ) / 원래의 총 리뷰 수
   *
   * @param prevScore  이전 평점
   * @param afterScore 새로 들어온 평점
   */
  public void updateRateByUpdate(Double prevScore, Double afterScore) {
    this.rate = (this.rate * this.reviews.size() - prevScore + afterScore) / (this.reviews.size());
  }

  /**
   * 평점을 업데이트 한다. - 이전 리뷰의 삭제로 인해
   * 리뷰가 하나밖에 없으면 0점. (나누기 0은 오류 발생)
   * 총 평점 (원래 총 평점 (원래 평점 * 원래 리퓨의 수) - 삭제된 평점) / 총 리뷰 수 (원래 리뷰의 수 -1)
   *
   * @param score 삭제되는 점수
   */
  public void updateRateByDelete(Double score) {
    this.rate =
        this.reviews.size() != 1
            ? (this.rate * this.reviews.size() - score) / (this.reviews.size() - 1)
            : 0;
  }
}
