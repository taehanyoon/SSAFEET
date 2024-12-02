package com.ssafy.ssafeet.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.ssafy.ssafeet.model.dto.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private String key = "SSAFY_NonMajor_JavaTrack_SecretKey";
	private SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

	
	public String createToken(User user) {
		Date exp = new Date(System.currentTimeMillis()+ 1000*60*60*24*365);
		return Jwts.builder().header().add("typ", "JWT").and()
				.claim("id", user.getId()).claim("campus", user.getCampus()).claim("name", user.getName()).expiration(exp)
				.signWith(secretKey).compact();
	}
	
	public Jws<Claims> validate(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
	}
	
	public int getUserIdFromToken(String token) {
        Jws<Claims> claims = validate(token);
        return claims.getBody().get("id", Integer.class);
    }
}
