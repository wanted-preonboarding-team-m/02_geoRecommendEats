package com.wanted.domain.review.api;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.wanted.domain.auth.application.AuthService;
import com.wanted.domain.review.application.ReviewService;
import com.wanted.domain.review.dto.request.ReviewWriteReqDto;
import com.wanted.domain.review.dto.response.ReviewsInRestaurantResDto;
import com.wanted.global.util.format.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  /**
   * 식당의 모든 리뷰 조회
   *
   * @param restaurantId 식당 id
   * @return 200, 식당의 모든 리뷰 데이터
   */
  @GetMapping("/restaurants/{restaurantId}")
  public ResponseEntity<ApiResponse> readAllReviewsInRestaurant(
      @PathVariable("restaurantId") Long restaurantId
  ) {
    ReviewsInRestaurantResDto resDto = reviewService.readAllReviewsInRestaurant(restaurantId);

    return ResponseEntity.ok(
        ApiResponse.toSuccessForm(resDto));
  }

  /**
   * 리뷰 수정
   * 이전에 회원이 작성한 리뷰가 없으면 예외 처리.
   * 이전 리뷰의 평점과 수정된 리뷰의 평점을 이용하여, 식당의 평균 평점 수정
   *
   * @param token        JWT 토큰
   * @param memberId     회원 Id
   * @param restaurantId 식당 Id
   * @param reqDto       리뷰 작성 데이터
   * @return 201, 수정된 리뷰 Id
   */
  @PutMapping("/members/{memberId}/{restaurantId}")
  public ResponseEntity<ApiResponse> updateReview(
      @RequestHeader(AUTHORIZATION) String token,
      @PathVariable("memberId") Long memberId,
      @PathVariable("restaurantId") Long restaurantId,
      @Valid @RequestBody ReviewWriteReqDto reqDto
  ) {
    // 토큰의 유저 account 와 리뷰를 작성할 유저 account 는 같아야 한다.
    authService.validSameTokenAccount(token, memberId);

    Long reviewId = reviewService.updateReview(memberId, restaurantId, reqDto);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.toSuccessForm(reviewId));
  }

  /**
   * 리뷰 삭제
   *
   * @param token        JWT 토큰
   * @param memberId     회원 Id
   * @param restaurantId 식당 Id
   */
  @DeleteMapping("/members/{memberId}/{restaurantId}")
  public ResponseEntity<ApiResponse> deleteReview(
      @RequestHeader(AUTHORIZATION) String token,
      @PathVariable("memberId") Long memberId,
      @PathVariable("restaurantId") Long restaurantId
  ) {
    // 토큰의 유저 account 와 리뷰를 작성할 유저 account 는 같아야 한다.
    authService.validSameTokenAccount(token, memberId);

    reviewService.deleteReview(memberId, restaurantId);

    return ResponseEntity.status(HttpStatus.OK)
        .body(ApiResponse.toSuccessForm(""));
  }
}
