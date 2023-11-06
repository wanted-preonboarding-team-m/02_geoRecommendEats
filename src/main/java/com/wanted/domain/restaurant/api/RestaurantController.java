package com.wanted.domain.restaurant.api;

import com.wanted.domain.restaurant.application.RestaurantService;
import com.wanted.domain.restaurant.dto.restaurant.response.RestaurantRecomResDto;
import com.wanted.global.util.format.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    /**
     * 맛집 목록 조회
     *
     * @param lat
     * @param lon
     * @param range
     * @param sort
     * @return 맛집 목록 (lat 위도, lon 경도, type 종류, workplcae_name 사업장명,
     *                  road_name_address 소재지 도로명 주소, lot_number_address 소재지 지번 주소, zip_code 우편번호,
     *                  rate 평점, distance 거리)
     */
    @GetMapping()
    public ResponseEntity<ApiResponse> getRecommendRestaurant(
        @RequestParam(required = true) String lat,
        @RequestParam(required = true) String lon,
        @RequestParam(required = true) Double range,
        @RequestParam(defaultValue = "distance") String sort
    ){
        List<RestaurantRecomResDto> restaurants = restaurantService.getRecommendRestaurant(lat,lon,range,sort);

        ApiResponse apiResponse = ApiResponse.toSuccessForm(restaurants);

        return ResponseEntity.ok(apiResponse);
    }
}