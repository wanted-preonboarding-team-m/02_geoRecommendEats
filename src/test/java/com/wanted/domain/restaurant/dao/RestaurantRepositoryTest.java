package com.wanted.domain.restaurant.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.wanted.domain.restaurant.application.RestaurantService;
import com.wanted.domain.restaurant.constant.FoodType;
import com.wanted.domain.restaurant.dto.restaurant.response.RestaurantRecomResDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Nested
    @DisplayName("맛집 조회 테스트")
    class findRestaurantsWithinRange{
        @Test
        @DisplayName("맛집 조회에 성공한다.")
        void 맛집_조회_성공() {
            double lat = 37.2040;
            double lon = 127.07596008849987;
            double range = 2.0;


            //조회할 맛집 범위 생성
            double minLat = lat - (0.009009 * range);
            double maxLat = lat + (0.009009 * range);
            double minLon = lon - (0.01127 * range);
            double maxLon = lon + (0.01127 * range);

            List<RestaurantRecomResDto> list = restaurantRepository.findRestaurantsWithinRange(
                minLat, minLon, maxLat, maxLon);

            assertThat(list).isNotEmpty();

            /**
             * 어떻게 출력되는지 확인차 출력 로직을 구현했습니다. 이 코드를 제거하면 테스트 시간이 971ms -> 947ms 로 감소합니다.
             */
//        for (RestaurantRecomResDto restaurant : list
//        ) {
//            List<Object> valuesList = Arrays.asList(
//                restaurant.getId(),
//                restaurant.getLat(),
//                restaurant.getLon(),
//                restaurant.getType(),
//                restaurant.getWorkplcaeName(),
//                restaurant.getRoadNameAddress(),
//                restaurant.getLotNumberAddress(),
//                restaurant.getZipCode()
//            );
//            String joinedString = valuesList.stream()
//                .map(Object::toString)
//                .collect(Collectors.joining(", "));
//
//            System.out.println(joinedString);
//        }
        }

        @Test
        @DisplayName("맛집 조회에 실패한다.")
        void 맛집_조회_실패() {
            double lat = 37.2040;
            double lon = 127.07596008849987;
            int range = 0; //범위를 0으로 주면 맛집이 출력되지 않음

            double minLat = lat - (0.009009 * range);
            double maxLat = lat + (0.009009 * range);
            double minLon = lon - (0.01127 * range);
            double maxLon = lon + (0.01127 * range);

            List<RestaurantRecomResDto> list = restaurantRepository.findRestaurantsWithinRange(minLat,
                minLon, maxLat, maxLon);

            assertThat(list).isEmpty();
        }
    }

}
