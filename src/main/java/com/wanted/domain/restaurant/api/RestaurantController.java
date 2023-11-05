package com.wanted.domain.restaurant.api;

import com.wanted.domain.restaurant.application.RestaurantService;
import com.wanted.domain.restaurant.dto.restaurant.response.RestaurantRecomResDto;
import com.wanted.global.util.format.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    /**
     * 맛집 목록 조회
     *
     * @return 200
     */
    @GetMapping()
    public ResponseEntity<ApiResponse> getRecommendRestaurant(
        @RequestParam String lat,
        @RequestParam String lon,
        @RequestParam Double range,
        @RequestParam(defaultValue = "거리순") String sort
    ){
        List<RestaurantRecomResDto> restaurants = restaurantService.getRecommendRestaurant(lat,lon,range,sort);

        ApiResponse apiResponse = ApiResponse.toSuccessForm(restaurants);

        return ResponseEntity.ok(apiResponse);
    }

}
