package com.wanted.domain.restaurant.dao;

import com.wanted.domain.restaurant.dto.restaurant.response.RestaurantDetailResDto;
import com.wanted.domain.restaurant.dto.restaurant.response.RestaurantRecomResDto;
import com.wanted.domain.restaurant.entity.Restaurant;
import java.util.List;
import java.util.Optional;
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

    /**
     * 맛집 상세 조회
     *
     * @param id
     * @return 맛집 상세 정보(open api에서 받아온 모든 필드 포함)
     */
    @Query("SELECT new com.wanted.domain.restaurant.dto.restaurant.response.RestaurantDetailResDto(" +
        "r.id," +
        "rt.type," +
        "re.totalNumber, re.maleNumber, re.femaleNumber," +
        "rf.ratingName, rf.WaterFacilityName, rf.restaurantAvailable,rf.facilityScale, rf.madeYear, rf.closeDate," +
        "rh.industryName, rh.businessName," +
        "rl.lat, rl.lon, rl.sigunName, rl.sigunCode," +
        "rs.roadNameAddress, rs.lotNumberAddress, rs.zipCode, rs.area," +
        "rw.workplaceName, rw.licenseDate, rw.businessStatus, rw.classificationName)" +
        "FROM Restaurant r " +
        "JOIN r.restaurantType rt " +
        "JOIN r.restaurantEmployee re " +
        "JOIN r.restaurantFacility rf " +
        "JOIN r.restaurantHygiene rh " +
        "JOIN r.restaurantLocation rl " +
        "JOIN r.restaurantSite rs " +
        "JOIN r.restaurantWorkplace rw " +
        "WHERE r.id = :id")
    Optional<RestaurantDetailResDto> findRestaurantById(Long id);
}
