package com.wanted.domain.restaurant.api.location;

import com.wanted.domain.restaurant.application.location.LocationService;
import com.wanted.domain.restaurant.dto.location.response.CsvResponseDto;
import com.wanted.global.util.format.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/location")
public class LocationController {

  private final LocationService locationService;

  /**
   * 전체 시구군 조회
   *
   * @return 200 , 전체 시구군 정보(dosi(도시), sigungu(시군구) lat(y축) ,lon(x축))
   */
  @GetMapping
  public ResponseEntity<ApiResponse> getAllLocations() {
    List<CsvResponseDto> locations = locationService.getAllLocations();

    ApiResponse apiResponse = ApiResponse.toSuccessForm(locations);

    return ResponseEntity.ok(apiResponse);
  }


}
