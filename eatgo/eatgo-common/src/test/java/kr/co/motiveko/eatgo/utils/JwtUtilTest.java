package kr.co.motiveko.eatgo.utils;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import io.jsonwebtoken.Claims;

public class JwtUtilTest {

	private static final String SECRET="12345678901234567890123456789012";
	
	private JwtUtil jwtUtil;
	
	@Before
	public void setUp() {
		jwtUtil = new JwtUtil(SECRET);
	}
	
	@Test
	public void createToken() {
		
		
		String token = jwtUtil.createToken(1004L, "John");
		assertThat(token, containsString("."));
	}
	
	@Test
	public void getClaims() {
		
		String token="eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.8hm6ZOJykSINHxL-rf0yV882fApL3hyQ9-WGlJUyo2A";
		Claims claims = jwtUtil.getClaims(token);

		assertThat(claims.get("name"), is("John"));
		assertThat(claims.get("userId", Long.class), is(1004L)); //일반숫자는 그냥해도 되고 Long같은거는 클래스 넣어줘야한다.
		
	}
}
