package kr.co.motiveko.eatgo.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import kr.co.motiveko.eatgo.utils.JwtUtil;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter{

	private JwtUtil jwtUtil;
	
	public JwtAuthenticationFilter(
			AuthenticationManager authenticationManager,  JwtUtil jwtUtil) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
	}

	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {
		Authentication authentication = getAuthentication(request);
		
		if(authentication != null) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		// TODO: JWT 분석
		chain.doFilter(request, response); // authentication이 있든없든 doFilter는 항상 사용.
	}

	//스프링 내부에서 사용 될 authentication
	private Authentication getAuthentication(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		if(token==null) {
			return null;
		}
		
		// 우리가 request에서 얻은 token은 {Authorization: Bearer TOKEN.123123124 }이런식으로 저장되있다
		// 앞에 "Bearer "를 잘라주기위해 .substring쓴다.
		Claims claims = jwtUtil.getClaims(token.substring("Bearer ".length()));
		
		// UsernamePasswordAuthenticationToken :: AbstractAuthenticationToken 를 상속받은것. 일반적으로 많이 쓰인다
		// 커스텀하여려면 직접  AbstractAuthenticationToken을 상속받는 클래스를 구현하면 된다.
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(claims, null);
		return authentication;
	}
	
	
}

