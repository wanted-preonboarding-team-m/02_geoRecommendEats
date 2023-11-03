package com.wanted.domain.user.entity.location;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.wanted.global.config.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유저의 위치 엔티티
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "user_location")
public class UserLocation extends BaseTimeEntity {

  // 유저 위치의 아이디
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "user_location_id", nullable = false)
  private Long id;

  // 위도
  @Column(name = "lat", nullable = false)
  private Double lat;

  // 경도
  @Column(name = "logt", nullable = false)
  private Double logt;
}
