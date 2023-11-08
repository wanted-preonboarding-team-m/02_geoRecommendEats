package com.wanted.domain.review.dto.request;

import com.wanted.domain.member.entity.Member;
import com.wanted.domain.restaurant.entity.Restaurant;
import com.wanted.domain.review.entity.Review;
import com.wanted.global.annotation.valid.review.ValidScoreValue;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 리뷰를 작성할 때 데이터를 받는 req Dto
 */
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewWriteReqDto {

  // 점수
  @ValidScoreValue(message = "1.0에서 5.0 사이인 0.5 단위의 점수를 입력해주세요")
  private Double score;

  // 내용
  @NotEmpty(message = "리뷰의 내용을 작성해주세요.")
  private String content;

  /**
   * 엔티티로 생성
   * 식당의 평점도 반영
   *
   * @param member     작성자
   * @param restaurant 리뷰 대상 식당
   * @return 리뷰 Entity
   */
  public Review toEntity(Member member, Restaurant restaurant) {
    restaurant.updateRateByWrite(this.score);

    return Review.builder()
        .member(member)
        .restaurant(restaurant)
        .content(this.content)
        .score(this.score)
        .build();
  }
}