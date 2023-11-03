package com.wanted.domain.member.dao.location;

import com.wanted.domain.member.entity.location.MemberLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLocationRepository extends JpaRepository<MemberLocation, Long> {

}
