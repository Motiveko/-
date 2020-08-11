package kr.co.motiveko.eatgo;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.motiveko.eatgo.filters.JwtAuthenticationFilter;
import kr.co.motiveko.eatgo.utils.JwtUtil;

// spring core에서도 배웠던 @configuration 
@Configuration
// websecurity 사용한다.
@EnableWebSecurity 
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter { //상속!
	
	@Value("${jwt.secret}")
	private String secret;
	

	@Override
	protected void configure( HttpSecurity http) throws Exception {
		
		// authenticationManager() ::WebSecurityConfigurerAdapter 에 구현되있음
		// 와 jwtUtil은 Bean으로 등록되어있기때문에  configure에서는 methodcall로 주입해준다!
		Filter filter = 
				new JwtAuthenticationFilter(authenticationManager(), jwtUtil());
		
		//로그인 폼을 없애줄것이다!, 이외에도 여러가지 disable 가능하다.
		http.formLogin().disable();
		// 이런것들 보안에서 중요하나 restAPI를 만들때에는 API Server에서는 불필요하므로 disable시킨다.
		http.csrf().disable();
		http.cors().disable();	//cross origin~~
		
		http.headers().frameOptions().disable()
					.and().addFilter(filter)
					// session정책 : 따로 session에 대해 관리해주지 않고 token을 받은걸로 filter가 작업을 수행
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
	}
	
	//UserService에서 사용할 빈!
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Bean등록!
	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil(secret);
	}
}
