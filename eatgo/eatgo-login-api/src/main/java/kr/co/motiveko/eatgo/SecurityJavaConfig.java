package kr.co.motiveko.eatgo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	
		
		//로그인 폼을 없애줄것이다!, 이외에도 여러가지 disable 가능하다.
		http.formLogin().disable()
			.csrf().disable()
			.cors().disable()
			.headers().frameOptions().disable();
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
