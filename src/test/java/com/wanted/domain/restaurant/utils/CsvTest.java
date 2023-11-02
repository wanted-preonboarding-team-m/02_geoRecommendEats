package com.wanted.domain.restaurant.utils;

import com.wanted.domain.restaurant.dto.location.response.CsvResponseDto;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


public class CsvTest {

  private static Map<String, List<CsvResponseDto>> saveLines = new LinkedHashMap<>();

  @BeforeEach
  void readLine() throws IOException {
    BufferedReader br = Files.newBufferedReader(Paths.get("src/test/resources/sgg_lat_lon.csv"));
    try (br) {
      // 첫 번째 줄 (헤더) 건너뛰기
      br.readLine();
      String[] lines = {};
      String line;
      List<CsvResponseDto> csvResponseDtoList = new ArrayList<>();

      while ((line = br.readLine()) != null) {
        lines = line.split(",");
        CsvResponseDto csvResponseDto = CsvResponseDto.builder()
            .doSi(lines[0])
            .siGunGu(lines[1])
            .lon(lines[2])
            .lat(lines[3])
            .build();
        csvResponseDtoList.add(csvResponseDto);
        saveLines.computeIfAbsent(lines[0], k -> new ArrayList<>()).add(csvResponseDto);
        // 키가 존재하지 않으면 새로만든다.
        // ex) 경기..경기 .add .add .... ..
        // 충청 ?! computeIfAbsent 시작
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

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
  void linesData를_Dto_변환이후_Map_넣고_강원에_데이터가_18개인지확인() {
    int MAX_LEN_GANGWON = 18;
    Assertions.assertEquals(MAX_LEN_GANGWON, saveLines.get("강원").size());
  }
}
