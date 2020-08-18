package kr.co.motiveko.eatgo.application;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.motiveko.eatgo.domain.EatgoUser;
import kr.co.motiveko.eatgo.domain.UserRepository;

@Service
@Transactional
public class UserService {

	UserRepository userRepository;
	PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository repository,PasswordEncoder passwordEncoder) {
		this.userRepository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	public EatgoUser authenticate(String email, String password) {
		
		EatgoUser user = userRepository.findByUserEmail(email)
				.orElseThrow(() -> new EmailNotExistedException(email));
		
		//  패스워드 일치하지 않는 경우 체크
					//matches도 mocking을 해줘야한다
		if(!passwordEncoder.matches(password, user.getUserPassword())) {			
			throw new PasswordWrongException();
		}
		
		return user;
	}
	
}
