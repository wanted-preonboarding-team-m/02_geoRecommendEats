package com.wanted.domain.review.entity;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.wanted.domain.member.entity.Member;
import com.wanted.domain.restaurant.entity.Restaurant;
import com.wanted.global.config.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
@Table(name = "review")
public class Review extends BaseTimeEntity {

  // 리뷰 Id
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "review_id", nullable = false)
  private Long id;

  // 작성자 (N:1)
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  // 리뷰 대상 음식점 (N:1)
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "restaurant_id", nullable = false)
  private Restaurant restaurant;

  // 점수
  @Column(name = "score", nullable = false)
  private Double score;

  // 내용
  @Column(name = "content", nullable = false)
  private String content;

  @Builder
  public Review(Long id, Member member, Restaurant restaurant, Double score, String content) {
    this.id = id;
    this.member = member;
    this.restaurant = restaurant;
    this.score = score;
    this.content = content;
  }
}
