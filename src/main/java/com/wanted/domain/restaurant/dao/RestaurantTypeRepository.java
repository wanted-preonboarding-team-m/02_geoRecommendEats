package com.wanted.domain.restaurant.dao;

import com.wanted.domain.restaurant.entity.RestaurantType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTypeRepository extends JpaRepository<RestaurantType, Long> {

}
