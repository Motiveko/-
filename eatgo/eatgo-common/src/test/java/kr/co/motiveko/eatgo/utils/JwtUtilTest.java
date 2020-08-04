package kr.co.motiveko.eatgo.utils;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

public class JwtUtilTest {

	@Test
	public void createToken() {
		
		String secret="12345678901234567890123456789012";
		JwtUtil jwtUtil = new JwtUtil(secret);
		
		String token = jwtUtil.createToken(1004L, "John");
		assertThat(token, containsString("."));
	}
}
