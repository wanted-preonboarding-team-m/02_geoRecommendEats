package com.wanted.domain.user.entity;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import com.wanted.domain.user.entity.location.MemberLocation;
import com.wanted.global.config.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유저의 엔티티
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
public class Member extends BaseTimeEntity {

  public static final int MAX_ACCOUNT_LENGTH = 20;
  public static final int MAX_PASSWORD_LENGTH = 256;

  // 유저의 아이디
  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "member_id", nullable = false)
  private Long id;

  // 유저 위치 (1:1)
  @OneToOne(fetch = LAZY)
  @JoinColumn(name = "member_location_id", nullable = true)
  private MemberLocation memberLocation;

  // 계정 명
  @Column(name = "account", nullable = false, length = MAX_ACCOUNT_LENGTH)
  private String account;

  // 비밀번호
  @Column(name = "password", nullable = false, length = MAX_PASSWORD_LENGTH)
  private String password;
}
