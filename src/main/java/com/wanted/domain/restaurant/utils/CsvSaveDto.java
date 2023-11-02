package com.wanted.domain.restaurant.utils;

import com.wanted.domain.restaurant.dto.location.response.CsvResponseDto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.springframework.stereotype.Component;


@Getter
@Component
public class CsvSaveDto {

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
    this.csvParserCollection.computeIfAbsent(lines[0], k -> new ArrayList<>()).add(csvResponseDto);
  }

  public int getCsvParserSize() {
    return this.csvParserCollection.size();
  }

}
