package com.wanted.domain.member.dto.response;

import com.wanted.domain.member.entity.Member;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원의 정보 데이터를 전달하는 dto
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoResDto {

  // 회원 id
  private String account;
  // 생성 시간
  private LocalDateTime createdAt;
  // 수정 시간
  private LocalDateTime updatedAt;
  // 권한
  private String authority;

  public MemberInfoResDto(Member member) {
    this.account = member.getAccount();
    this.createdAt = member.getCreatedAt();
    this.updatedAt = member.getUpdatedAt();
    this.authority = member.getAuthority().toString();
  }
}
