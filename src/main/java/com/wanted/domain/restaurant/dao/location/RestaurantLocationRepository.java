package com.wanted.domain.restaurant.dao.location;

import com.wanted.domain.restaurant.entity.location.RestaurantLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantLocationRepository extends JpaRepository<RestaurantLocation, Long> {

}
