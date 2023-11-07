package com.wanted.domain.review.api;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.wanted.domain.auth.application.AuthService;
import com.wanted.domain.review.application.ReviewService;
import com.wanted.domain.review.dto.request.ReviewWriteReqDto;
import com.wanted.global.util.format.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;
  private final AuthService authService;

  /**
   * 리뷰 작성
   *
   * @param memberId     회원 Id
   * @param restaurantId 식당 Id
   * @param reqDto       리뷰 작성에 필요한 데이터 req dto
   * @return 201, 작성된 리뷰 Id
   */
  @PostMapping("/members/{memberId}/{restaurantId}")
  public ResponseEntity<ApiResponse> writeReview(
      @RequestHeader(AUTHORIZATION) String token,
      @PathVariable("memberId") Long memberId,
      @PathVariable("restaurantId") Long restaurantId,
      @Valid @RequestBody ReviewWriteReqDto reqDto
  ) {
    // 토큰의 유저 account 와 리뷰를 작성할 유저 account 는 같아야 한다.
    authService.validSameTokenAccount(token, memberId);

    Long reviewId = reviewService.writeReview(memberId, restaurantId, reqDto);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.toSuccessForm(reviewId));
  }
}
