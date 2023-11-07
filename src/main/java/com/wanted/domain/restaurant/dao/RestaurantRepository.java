package com.wanted.domain.restaurant.dao;

import com.wanted.domain.restaurant.dto.restaurant.response.RestaurantRecomResDto;
import com.wanted.domain.restaurant.entity.Restaurant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    /**
     * 사용자가 지정한 범위 이내 맛집 조회
     *
     * @param minLat
     * @param minLon
     * @param maxLat
     * @param maxLon
     * @return List<RestaurantRecomResDto> 맛집 목록 리스트
     */
    @Query("SELECT new com.wanted.domain.restaurant.dto.restaurant.response.RestaurantRecomResDto(" +
              "r.id," +
              "rl.lat, rl.lon," +
              "rt.type," +
              "rw.workplaceName," +
              "rs.roadNameAddress, rs.lotNumberAddress, rs.zipCode," +
              "r.rate)" +
        "FROM Restaurant r " +
        "JOIN r.restaurantLocation rl " +
        "JOIN r.restaurantType rt " +
        "JOIN r.restaurantWorkplace rw " +
        "JOIN r.restaurantSite rs " +
        "WHERE rl.lat BETWEEN :minLat AND :maxLat " +
          "AND rl.lon BETWEEN :minLon AND :maxLon ")
    List<RestaurantRecomResDto> findRestaurantsWithinRange(
        @Param("minLat") double minLat,
        @Param("minLon") double minLon,
        @Param("maxLat") double maxLat,
        @Param("maxLon") double maxLon);
}
