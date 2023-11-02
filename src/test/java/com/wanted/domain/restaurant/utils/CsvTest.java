package com.wanted.domain.restaurant.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CsvTest {

  /**
   * @CsvFileSource 헤더 제외하고 resources/sgg_lat_lon.csv 를 읽어온다
   * @ParameterizedTest 링크에 의하면 csv를 읽고 각 값을 받아올려면 필요함
   * https://www.baeldung.com/parameterized-tests-junit-5
   */
  @CsvFileSource(resources = "/sgg_lat_lon.csv", numLinesToSkip = 1)
  @ParameterizedTest
  void temp_패키지에_있는_csv파일을_정상적으로_읽어온다(String dosi, String sgg, String lat, String lon) {
    Assertions.assertNotNull(dosi);
    Assertions.assertNotNull(sgg);
    Assertions.assertNotNull(lat);
    Assertions.assertNotNull(lon);
  }
}
