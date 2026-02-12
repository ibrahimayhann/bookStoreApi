package com.ibrahimayhan.repository;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibrahimayhan.entities.RefreshToken;
import com.ibrahimayhan.entities.User;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

	 	Optional<RefreshToken> findByTokenHash(String tokenHash);

	    void deleteByUser(User user);//tüm refreshtokenları siler - logout All devices

	    void deleteByExpiresAtBefore(Instant now);//süresi dolmuş tüm tokenları siler
}
