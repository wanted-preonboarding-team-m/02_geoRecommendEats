package com.wanted.domain.restaurant.dao.employee;

import com.wanted.domain.restaurant.entity.employee.RestaurantEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantEmployeeRepository extends JpaRepository<RestaurantEmployee, Long> {

}
