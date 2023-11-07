package com.wanted.domain.auth.dao.token;

import com.wanted.domain.auth.entity.token.RefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

  Optional<RefreshToken> findByKey(String key);

  void deleteByKey(String key);

}
