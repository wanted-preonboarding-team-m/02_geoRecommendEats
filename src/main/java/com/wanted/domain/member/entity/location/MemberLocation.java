package com.wanted.domain.member.entity.location;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.wanted.global.config.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유저의 위치 엔티티
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
@Table(name = "member_location")
public class MemberLocation extends BaseTimeEntity {

  // 유저 위치의 아이디
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "member_location_id", nullable = false)
  private Long id;

  // 위도
  @Column(name = "lat", nullable = false)
  private Double lat;

  // 경도
  @Column(name = "logt", nullable = false)
  private Double logt;

  /**
   * Entity 로 수정
   *
   * @param memberLocation Entity
   */
  public void update(MemberLocation memberLocation) {
    this.lat = memberLocation.getLat();
    this.logt = memberLocation.getLogt();
  }
}
