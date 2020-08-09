package kr.co.motiveko.eatgo.utils;

import java.security.Key;

import io.jsonwebtoken.Claims;
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
		return Jwts.builder()
				.claim("userId", userId)	// body(payload) 에 key:value!
				.claim("name",name)
				.signWith(key,  SignatureAlgorithm.HS256)
				.compact();
	}
	public Claims getClaims(String token) {
		return Jwts.parser()
					.setSigningKey(key)
					.parseClaimsJws(token)   // Jws : sign이 포함된 jwt
					.getBody();
	}
}
