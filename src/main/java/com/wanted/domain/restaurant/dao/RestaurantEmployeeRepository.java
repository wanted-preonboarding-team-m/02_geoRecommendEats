package com.wanted.domain.restaurant.dao;

import com.wanted.domain.restaurant.entity.RestaurantEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantEmployeeRepository extends JpaRepository<RestaurantEmployee, Long> {

}
