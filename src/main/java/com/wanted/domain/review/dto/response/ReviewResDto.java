package com.wanted.domain.review.dto.response;

import com.wanted.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResDto {

  // 리뷰 아이디
  private Long reviewId;
  // 평점
  private Double score;
  // 내용
  private String content;
  // 작성자 계정 id
  private String userAccount;

  public ReviewResDto(Review review) {
    this.reviewId = review.getId();
    this.score = review.getScore();
    this.content = review.getContent();
    this.userAccount = review.getMember().getAccount();
  }
}
