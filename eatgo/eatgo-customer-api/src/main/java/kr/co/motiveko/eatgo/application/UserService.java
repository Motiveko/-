package kr.co.motiveko.eatgo.application;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.motiveko.eatgo.domain.User;
import kr.co.motiveko.eatgo.domain.UserRepository;

@Service
@Transactional
public class UserService {

	UserRepository userRepository;
	PasswordEncoder passwordEncoder;
	
	//  Implicit Constructor Injection! -> constructor만 있으면  @Autowired가 필요가 없다.
	public UserService(UserRepository repository,PasswordEncoder passwordEncoder) {
		this.userRepository = repository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User registerUser(String email, String name, String password) {
		// email중복 방지
		Optional<User> existed= userRepository.findByEmail(email);
		if(existed.isPresent()) {
			throw new EmailExistedException(email);
		}
		
		// 암호화
		// PasswordEncoder :: interface, BCryptPasswordEncoder :: 구현체		
		String encodedePassword = passwordEncoder.encode(password);
				
		User user = User.builder()
						.email(email)
						.name(name)
						.password(encodedePassword )
						.level(1L) // NotNull!
						.build();
		return userRepository.save(user);
	}
	public User authenticate(String email, String password) {
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new EmailNotExistedException(email));
		
		//  패스워드 일치하지 않는 경우 체크
					//matches도 mocking을 해줘야한다
		if(!passwordEncoder.matches(password, user.getPassword())) {			
			throw new PasswordWrongException();
		}
		
		return user;
	}
	
}
