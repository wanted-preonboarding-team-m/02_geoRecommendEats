package com.wanted.domain.restaurant.application.location;

import com.wanted.domain.restaurant.dto.location.response.CsvResponseDto;
import com.wanted.global.util.csv.CsvSaveDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final CsvSaveDto csvSaveDto;

    /**
     * 전체 시구군 조회
     *
     * @return CsvResponseDto 리스트 , fields: dosi(도시), sigungu(시군구) lat(y축) ,lon(x축)
     */
    public List<CsvResponseDto> getAllLocations(){
        return csvSaveDto.getAllDosiAndSigungu();
    }

}
