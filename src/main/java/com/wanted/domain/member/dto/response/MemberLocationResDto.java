package com.wanted.domain.member.dto.response;

import com.wanted.domain.member.entity.location.MemberLocation;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 위치 Entity의 정보를 전달하는 dto
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLocationResDto {

  // 위도
  private Double lat;
  // 경도
  private Double logt;
  // 생성 시간
  private LocalDateTime createdAt;
  // 수정 시간
  private LocalDateTime updatedAt;

  public MemberLocationResDto(MemberLocation memberLocation) {
    this.lat = memberLocation.getLat();
    this.logt = memberLocation.getLogt();
    this.createdAt = memberLocation.getCreatedAt();
    this.updatedAt = memberLocation.getUpdatedAt();
  }
}
