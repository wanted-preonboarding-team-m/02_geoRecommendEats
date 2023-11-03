package com.wanted.domain.user.dao.location;

import com.wanted.domain.user.entity.location.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<Long, UserLocation> {

}
