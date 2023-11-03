package com.wanted.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 오류 메시지와 상태를 쉽게 추가하기 위한 Enum
 */
@Getter
public enum ErrorCode {
  // open api 호출 오류
  JSON_PARSE_ERROR("JSON 파싱에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),


  //csv
  NOT_LOCATION("해당 지역이 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
  CSV_PARSER_ERROR("csv 파일을 다시 한번 확인해주세요.", HttpStatus.INTERNAL_SERVER_ERROR);

  //오류 메시지
  private final String message;
  //오류 상태코드
  private final HttpStatus httpStatus;

  ErrorCode(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }

}
