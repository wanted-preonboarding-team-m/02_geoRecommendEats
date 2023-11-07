package com.wanted.domain.member.dto.request;

import com.wanted.domain.member.entity.location.MemberLocation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유저 위치를 업데이트할 때 사용하는 데이터를 받는 dto
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLocationUpdateReqDto {

  // 위도
  @NotNull(message = "위도를 입력해주세요.")
  private Double lat;

  // 경도
  @NotNull(message = "경도를 입력해주세요.")
  private Double logt;

  public MemberLocation toEntity() {
    return MemberLocation.builder()
        .lat(this.lat)
        .logt(this.logt)
        .build();
  }
}
