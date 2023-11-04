package com.wanted.global.util.csv;

import com.wanted.domain.restaurant.dto.location.response.CsvResponseDto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * csv데이터 적재소
 */
@Getter
@Component
public class CsvSaveDto {

  // 데이터를 담아놓는 곳
  private Map<String, List<CsvResponseDto>> csvParserCollection = new LinkedHashMap<>();

  public void save(String line) {
    String[] lines = line.split(",");
    List<CsvResponseDto> responseDtoList = new ArrayList<>();
    CsvResponseDto csvResponseDto = CsvResponseDto.builder()
        .doSi(lines[0])
        .siGunGu(lines[1])
        .lon(lines[2])
        .lat(lines[3])
        .build();
    responseDtoList.add(csvResponseDto);
    // map에 새로운 키가 등장하면 새롭게 추가하는 메서드
    this.csvParserCollection.computeIfAbsent(lines[0], k -> new ArrayList<>()).add(csvResponseDto);
  }

  public int getCsvParserSize() {
    return this.csvParserCollection.size();
  }

  /**
   * getMatcherDosiAndSigungu: 원하는 지역 및 시군구 를 통해서 CsvResponseDto를 반환 한다.
   *
   * @param dosi    도,시
   * @param sigungu 시군구
   * @return CsvResponseDto  -> fields:  lat ,lon sigungu,dosi
   */
  public CsvResponseDto getMatcherDosiAndSigungu(String dosi, String sigungu) {
    List<CsvResponseDto> csvResponseDtoList = this.csvParserCollection.get(dosi);
    return csvResponseDtoList.stream()
        .filter(dto -> sigungu.equals(dto.getSiGunGu()))
        .findFirst()
        .orElseThrow();
  }

  /**
   * 전체 시군구를 반환한다.
   *
   * @return CsvResponseDto  -> fields:  lat ,lon sigungu,dosi
   */
  public List<CsvResponseDto> getAllDosiAndSigungu(){
    List<CsvResponseDto> allResponseDtoList =
        csvParserCollection.values().stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());

    return allResponseDtoList;
  }
}
