package com.wanted.domain.review.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.config.restdocs.AbstractRestDocsTests;
import com.wanted.domain.auth.application.AuthService;
import com.wanted.domain.review.ReviewTestHelper;
import com.wanted.domain.review.application.ReviewService;
import com.wanted.domain.review.dto.request.ReviewWriteReqDto;
import com.wanted.domain.review.dto.response.ReviewsInRestaurantResDto;
import com.wanted.domain.review.entity.Review;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@WebMvcTest(controllers = ReviewController.class)
class ReviewControllerTest extends AbstractRestDocsTests {

  private final static String REVIEW_URL = "/api/v1/reviews";

  @MockBean
  private ReviewService reviewService;
  @MockBean
  private AuthService authService;
  @Autowired
  private ObjectMapper objectMapper;

  @Nested
  @DisplayName("리뷰 작성 관련 테스트")
  class WriteReview {

    @Test
    @DisplayName("정상적인 데이터 일 때, 리뷰 작성에 성공한다.")
    void 정상적인_데이터_일_때_리뷰_작성에_성공한다() throws Exception {
      ReviewWriteReqDto reqDto =
          ReviewWriteReqDto.builder()
              .content("리뷰 내용")
              .score(4.5)
              .build();

      given(reviewService.writeReview(any(), any(), any())).willReturn(1L);

      mockMvc.perform(post(REVIEW_URL + "/members/1/1")
              .contentType(MediaType.APPLICATION_JSON)
              .header(HttpHeaders.AUTHORIZATION, "JWT_TOKEN")
              .content(objectMapper.writeValueAsString(reqDto)))
          .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("점수 형식이 맞지 않으면, 리뷰 작성에 실패한다.")
    void 점수_형식이_맞지_않으면_리뷰_작성에_실패한다() throws Exception {
      ReviewWriteReqDto reqDto =
          ReviewWriteReqDto.builder()
              .content("리뷰 내용")
              .score(4.3) // 잘못된 점수
              .build();

      given(reviewService.writeReview(any(), any(), any())).willReturn(1L);

      mockMvc.perform(post(REVIEW_URL + "/members/1/1")
              .contentType(MediaType.APPLICATION_JSON)
              .header(HttpHeaders.AUTHORIZATION, "JWT_TOKEN")
              .content(objectMapper.writeValueAsString(reqDto)))
          .andExpect(status().isBadRequest());
    }
  }

  @Nested
  @DisplayName("리뷰 조회 관련 테스트")
  class readReview {

    @Test
    @DisplayName("정상적으로 회사의 모든 리뷰를 반환한다.")
    void 정상적으로_회사의_모든_리뷰를_반환한다() throws Exception {
      List<Review> reviews = new ArrayList<>();
      for (int i = 1; i <= 3; i++) {
        reviews.add(ReviewTestHelper.createReviewWithId((long) i));
      }
      ReviewsInRestaurantResDto resDto = new ReviewsInRestaurantResDto(reviews);

      given(reviewService.readAllReviewsInRestaurant(1L)).willReturn(resDto);

      mockMvc.perform(get(REVIEW_URL + "/restaurants/1"))
          .andExpect(status().isOk());
    }
  }
}