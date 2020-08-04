package kr.co.motiveko.eatgo.utils;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

	private final Key key;
	
	public JwtUtil(String secret) {
		// secret : 256비트이상, 32글자(개당8bit) 이상
		this.key = Keys.hmacShaKeyFor(secret.getBytes()); // secret key
	}

	public String createToken(long userId, String name) {
	
		String token = Jwts.builder()
				.claim("UserId", userId)	// body(payload) 에 key:value!
				.claim("name",name)
				.signWith(key,  SignatureAlgorithm.HS256)
				.compact();
		
		return token;
	}

	
}
