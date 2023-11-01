package com.wanted.domain.restaurant.dao;

import com.wanted.domain.restaurant.entity.RestaurantLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantLocationRepository extends JpaRepository<RestaurantLocation, Long> {

}
