package com.wanted.domain.restaurant.application;

import com.wanted.domain.restaurant.dao.RestaurantRepository;
import com.wanted.domain.restaurant.dto.restaurant.response.RestaurantRecomResDto;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<RestaurantRecomResDto> getRecommendRestaurant(
        String lat, String lon, Double range, String sort){

        /** 1. 사용자가 지정한 range를 포함한 범위 안에, 최대 최소 위도와 경도 범위 설정
        *
        * 서울에서 위도(lat) 1km 차이는 약 0.00909
        * 서울에서 경도(lon) 1km 차이는 약 0.01127
        ** */
        double minLat = Double.parseDouble(lat) - (0.009009 * range);
        double maxLat = Double.parseDouble(lat) + (0.009009 * range);
        double minLon = Double.parseDouble(lon) - (0.01127 * range);
        double maxLon = Double.parseDouble(lon) + (0.01127 * range);

        /** 2. 지정한 범위 안에 있는 맛집 목록 조회 */
        List<RestaurantRecomResDto> restaurantsList = restaurantRepository.findRestaurantsWithinRange(minLat,minLon,maxLat,maxLon);

        /** 3. db에서 조회한 맛집과 현재 사용자 위치 거리를 계산해서 저장 */
        for (RestaurantRecomResDto restaurant : restaurantsList){
            double distance = findDistance(Double.parseDouble(lat), Double.parseDouble(lon), restaurant.getLat(), restaurant.getLon());
            restaurant.setDistance(distance);
        }

        /** 4. 평점 내림차순, 거리 오름차순 정렬 */
        if (sort.equals("rate")) {
            restaurantsList.sort(Comparator.comparingDouble(RestaurantRecomResDto::getRate).reversed());
        } else {
            restaurantsList.sort(Comparator.comparingDouble(RestaurantRecomResDto::getDistance));
        }

        /** 4. 최종 결과값 list로 반환 */
        return restaurantsList;
    }

    /**
     * 사용자 위치와 조회한 맛집 사이의 거리 계산
     *
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     *
     * @return 거리(km)
     */
    private double findDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        double R = 6371; // km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

}
