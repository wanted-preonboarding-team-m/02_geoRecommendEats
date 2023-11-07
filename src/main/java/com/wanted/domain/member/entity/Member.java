package com.wanted.domain.member.entity;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import com.wanted.domain.member.entity.location.MemberLocation;
import com.wanted.domain.restaurant.entity.Restaurant;
import com.wanted.domain.review.entity.Review;
import com.wanted.global.config.entity.BaseTimeEntity;
import com.wanted.global.error.BusinessException;
import com.wanted.global.error.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유저의 엔티티
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
public class Member extends BaseTimeEntity {

  public static final int MAX_ACCOUNT_LENGTH = 20;
  public static final int MAX_PASSWORD_LENGTH = 256;

  // 유저의 아이디
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "member_id", nullable = false)
  private Long id;

  // 유저 위치 (1:1)
  @OneToOne(fetch = LAZY)
  @JoinColumn(name = "member_location_id", nullable = true)
  private MemberLocation memberLocation;

  // 계정 명
  @Column(name = "account", nullable = false, length = MAX_ACCOUNT_LENGTH)
  private String account;

  // 비밀번호
  @Column(name = "password", nullable = false, length = MAX_PASSWORD_LENGTH)
  private String password;

  // 권한
  @Enumerated(STRING)
  @Column(name = "authority", nullable = false)
  private Authority authority;

  // 작성한 리뷰들 (1:N)
  @OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "member")
  private List<Review> reviews = new ArrayList<>();

  @Builder
  public Member(Long id, MemberLocation memberLocation, String account, String password,
      Authority authority) {
    this.id = id;
    this.memberLocation = memberLocation;
    this.account = account;
    this.password = password;
    this.authority = authority;
  }

  /**
   * 지역 업데이트
   *
   * @param memberLocation 업데이트할 지역
   */
  public void updateLocation(MemberLocation memberLocation) {
    this.memberLocation = memberLocation;
  }

  /**
   * 리뷰를 쓸 수 있는지 확인
   * 이미 해당 식당에 리뷰를 썼으면, 예외 처리
   *
   * @param restaurantId 대상 식당 id
   */
  public void validCanWriteReview(Long restaurantId) {
    boolean isCanWriteReview = this.getReviews().stream()
        .map(Review::getRestaurant)
        .map(Restaurant::getId)
        .noneMatch(id -> id.equals(restaurantId));

    if (!isCanWriteReview) {
      throw new BusinessException(restaurantId, "restaurantId", ErrorCode.REVIEW_DUPLICATE_WRITE);
    }
  }

  /**
   * 식당 id에 맞는 사용자 리뷰 찾기
   *
   * @param restaurantId 찾을 식당 Id
   * @return 찾은 리뷰
   */
  public Review findReviewMatchRestaurant(Long restaurantId) {
    Review foundReview = this.reviews.stream()
        .filter(r -> r.getRestaurant().getId().equals(restaurantId))
        .findFirst()
        .orElseThrow(
            () -> new BusinessException(restaurantId, "restaurantId", ErrorCode.REVIEW_NOT_FOUND)
        );

    return foundReview;
  }
}
