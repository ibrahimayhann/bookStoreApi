package com.ibrahimayhan.jwt;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ibrahimayhan.entities.RefreshToken;
import com.ibrahimayhan.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

	private final JwtProperties jwtProperties;
	
	
	//Secret key üretme
	private Key getSigningKey() {
		byte[] keyBytes=Decoders.BASE64.decode(jwtProperties.getSecret());
		
		return Keys.hmacShaKeyFor(keyBytes);
	}

	//Acces token üretme
	public String generateAccesToken(UserDetails userDetails) {
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+jwtProperties.getAccessTokenExpiration()))
				.signWith(getSigningKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	//Token içindeki tüm clasimsleri çözme
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	//kod tekrarını azaltır içine aldığı fonksiyona göre claimstan uygun içeriği döner
	private <T> T extractClaim(String token,Function<Claims,T> resolver) {
		final Claims claims=extractAllClaims(token);
		return resolver.apply(claims);
	}
	
	//getUsernameByToken
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	
	//token geçerli mi (username eşleşiyor mu && token süresi geçerli mi )
	public boolean isTokenValid(String token,UserDetails userDetails) {
		final String username=extractUsername(token);
		final boolean isTokenValid=extractClaim(token, Claims::getExpiration).before(new Date());
		
		return username.equals(userDetails.getUsername()) && isTokenValid;
	}
	
	
	
	public RefreshToken createRefreshToken(User user) {
		RefreshToken token=new RefreshToken();
		//token.setCreatedAt(new Date()); //new lemeye gerek yok entityde newledim
		token.setTokenHash(UUID.randomUUID().toString());
		token.setExpiresAt(Instant.now().plusMillis(jwtProperties.getRefreshTokenExpiration()));
		return token;
	}
	
	public boolean isRefreshTokenValid(RefreshToken refreshToken) {
		return refreshToken.getExpiresAt().isAfter(Instant.now());
	}
	
	
}
