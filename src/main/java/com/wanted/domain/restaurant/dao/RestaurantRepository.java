package com.wanted.domain.restaurant.dao;

import com.wanted.domain.restaurant.dto.restaurant.response.RestaurantRecomResDto;
import com.wanted.domain.restaurant.entity.Restaurant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT new com.wanted.domain.restaurant.dto.restaurant.response.RestaurantRecomResDto(" +
        "rl.lat, rl.lon, rl.sigunName, rl.sigunCode, rt.type, rw.workplaceName, " +
        "rs.roadNameAddress, rs.lotNumberAddress, rs.zipCode, r.rate) " +
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
