package com.wanted.domain.review.application;

import com.wanted.domain.member.dao.MemberRepository;
import com.wanted.domain.member.entity.Member;
import com.wanted.domain.restaurant.dao.RestaurantRepository;
import com.wanted.domain.restaurant.entity.Restaurant;
import com.wanted.domain.review.dao.ReviewRepository;
import com.wanted.domain.review.dto.request.ReviewWriteReqDto;
import com.wanted.domain.review.dto.response.ReviewsInRestaurantResDto;
import com.wanted.domain.review.entity.Review;
import com.wanted.global.error.BusinessException;
import com.wanted.global.error.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

  private final MemberRepository memberRepository;
  private final ReviewRepository reviewRepository;
  private final RestaurantRepository restaurantRepository;

  /**
   * 리뷰 작성
   * 작성자는 해당 식당에 작성한 기록이 있으면 작성이 되면 안된다.
   * 작성을 한 후, 식당의 평점 컬럼에 반영을 시켜야 한다.
   *
   * @param memberId     회원 Id
   * @param restaurantId 식당 Id
   * @param reqDto       리뷰 작성에 필요한 데이터 req dto
   * @return 작성된 리뷰 Id
   */
  @Transactional
  public Long writeReview(Long memberId, Long restaurantId, ReviewWriteReqDto reqDto) {

    // id로 회원을 가져온다.
    Member member = getMemberById(memberId);
    // 회원이 리뷰를 쓸 자격이 있는지 확인
    member.validCanWriteReview(restaurantId);

    Restaurant restaurant = getRestaurantById(restaurantId);

    // 가져온 Entity와 reqDto 바탕으로 Review Entity 생성 + 평점 반영
    Review review = reqDto.toEntity(member, restaurant);
    // 저장
    Long reviewId = reviewRepository.save(review).getId();

    return reviewId;
  }

  /**
   * 회원 id로 회원 찾기.
   * 없으면 404 예외 처리
   *
   * @param memberId 회원 id
   * @return 찾은 회원 entity
   */
  private Member getMemberById(Long memberId) {
    Member member = memberRepository.findById(memberId).orElseThrow(
        () -> new BusinessException(memberId, "memberId", ErrorCode.MEMBER_NOT_FOUND)
    );
    return member;
  }

  /**
   * 식당 id로 식당 찾기.
   * 없으면 404 예외 처리
   *
   * @param restaurantId 식당 id
   * @return 찾은 식당 entity
   */
  private Restaurant getRestaurantById(Long restaurantId) {
    Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
        () -> new BusinessException(restaurantId, "restaurantId", ErrorCode.RESTAURANT_NOT_FOUND)
    );
    return restaurant;
  }

  /**
   * 식당의 모든 리뷰 조회
   *
   * @param restaurantId 식당 id
   * @return 식당의 모든 리뷰 데이터
   */
  public ReviewsInRestaurantResDto readAllReviewsInRestaurant(Long restaurantId) {
    List<Review> reviews = getRestaurantById(restaurantId).getReviews();

    return new ReviewsInRestaurantResDto(reviews);
  }

  /**
   * 리뷰 수정
   * 이전에 회원이 작성한 리뷰가 없으면 예외 처리.
   * 이전 리뷰의 평점과 수정된 리뷰의 평점을 이용하여, 식당의 평균 평점 수정
   *
   * @param memberId     회원 Id
   * @param restaurantId 식당 Id
   * @param reqDto       리뷰 작성 데이터
   * @return 수정된 리뷰 Id
   */
  @Transactional
  public Long updateReview(Long memberId, Long restaurantId, ReviewWriteReqDto reqDto) {
    // id로 회원을 가져온다.
    Member member = getMemberById(memberId);
    // 회원이 식당에 관한 리뷰를 가져온다. 없으면 예외 처리.
    Review review = member.findReviewMatchRestaurant(restaurantId);
    // 이전 평점 저장
    Double prevScore = review.getScore();

    // 리뷰 수정
    review.update(reqDto);
    // 이후 평점 저장
    Double afterScore = review.getScore();

    // 식당의 평균 평점 수정
    getRestaurantById(restaurantId).updateRateByUpdate(prevScore, afterScore);

    return review.getId();
  }
}
