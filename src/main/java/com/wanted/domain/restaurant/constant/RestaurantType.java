package com.wanted.domain.restaurant.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 맛집 타입을 정의한 enum
 */
@RequiredArgsConstructor
@Getter
public enum RestaurantType {

  CHINESE("chinese", "Genrestrtchifood"),
  JAPANESE("japanese", "Genrestrtjpnfood"),
  FAST_FOOD("fastfood", "Genrestrtfastfood");

  // DB에 저장될 맛집 타입
  private final String type;

  // 경기도 공공데이터 포털에서 open api를 호출할 때 사용할 url endpoint
  private final String urlEndpoint;
}
