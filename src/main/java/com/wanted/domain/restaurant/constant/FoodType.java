package com.wanted.domain.restaurant.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 맛집 타입을 정의한 enum
 */
@RequiredArgsConstructor
@Getter
public enum FoodType {

  CHINESE_FOOD("Genrestrtchifood"),
  JAPANESE_FOOD("Genrestrtjpnfood"),
  FAST_FOOD("Genrestrtfastfood");

  // 경기도 공공데이터 포털에서 open api를 호출할 때 사용할 url endpoint
  private final String urlEndpoint;
}
