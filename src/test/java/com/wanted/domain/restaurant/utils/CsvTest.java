package com.wanted.domain.restaurant.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


public class CsvTest {


  /**
   * @CsvFileSource 헤더 제외하고 resources/sgg_lat_lon.csv 를 읽어온다
   * @ParameterizedTest 링크에 의하면 csv를 읽고 각 값을 받아올려면 필요함
   * https://www.baeldung.com/parameterized-tests-junit-5
   */
  @ParameterizedTest
  @CsvFileSource(resources = "/sgg_lat_lon.csv", numLinesToSkip = 1)
  void csv파일을_정상적으로_읽어온다(String dosi, String sgg, String lat, String lon) {
    Assertions.assertNotNull(dosi);
    Assertions.assertNotNull(sgg);
    Assertions.assertNotNull(lat);
    Assertions.assertNotNull(lon);
  }


  @Test
  void csv파일을_헤더를_건너뛰고_읽어온다() throws IOException {
    BufferedReader br = Files.newBufferedReader(Paths.get("src/test/resources/sgg_lat_lon.csv"));
    try (br) {
      // 첫 번째 줄 (헤더) 건너뛰기
      br.readLine();
      String line;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
