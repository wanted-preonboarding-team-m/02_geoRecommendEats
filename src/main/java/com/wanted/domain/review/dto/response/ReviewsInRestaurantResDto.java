package com.wanted.domain.review.dto.response;

import com.wanted.domain.review.entity.Review;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReviewsInRestaurantResDto {

  private List<ReviewResDto> reviews = new ArrayList<>();

  public ReviewsInRestaurantResDto(List<Review> reviews) {
    this.reviews = reviews.stream()
        .map(ReviewResDto::new)
        .collect(Collectors.toList());
  }
}
