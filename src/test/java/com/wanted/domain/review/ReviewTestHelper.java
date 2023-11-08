package com.wanted.domain.review;

import com.wanted.domain.member.MemberTestHelper;
import com.wanted.domain.restaurant.entity.Restaurant;
import com.wanted.domain.review.entity.Review;

public class ReviewTestHelper {

  public static Review createReviewWithId(Long reviewId) {
    return Review.builder()
        .id(reviewId)
        .score(4.5)
        .content("리뷰 내용")
        .restaurant(Restaurant.builder().id(1L).build()) //TODO RestaurantTestHelper 추가
        .member(MemberTestHelper.createMember())
        .build();
  }

}
