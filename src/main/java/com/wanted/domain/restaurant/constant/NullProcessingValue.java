package com.wanted.domain.restaurant.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * open api로 받아온 데이터의 속성 중, null값이 있으면 DB에 저장할 때 default 값으로 대체한다.
 * 이 후, DB에서 데이터를 가져올 때, default 값이면 "정보 없음"등의 값으로 대체한다.
 */
@Getter
@RequiredArgsConstructor
public enum NullProcessingValue {

  // String 타입의 default 값
  STRING_DEFAULT("no_information"),

  // Integer 타입의 default 값
  INTEGER_DEFAULT(-1),

  // Long 타입의 default 값
  LONG_DEFAULT(-1L),

  // Double 타입의 default 값
  DOUBLE_DEFAULT(-1.0);

  private final Object value;
}
