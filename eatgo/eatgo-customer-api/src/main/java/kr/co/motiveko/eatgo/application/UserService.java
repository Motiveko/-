package kr.co.motiveko.eatgo.application;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.motiveko.eatgo.domain.User;
import kr.co.motiveko.eatgo.domain.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public UserService(UserRepository repository) {
		this.userRepository = repository;
	}
	public User registerUser(String email, String name, String password) {
		// email중복 방지
		Optional<User> existed= userRepository.findByEmail(email);
		if(existed.isPresent()) {
			throw new EmailExistedException(email);
		}
		
		// 암호화
		// PasswordEncoder :: interface, BCryptPasswordEncoder :: 구현체		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedePassword = passwordEncoder.encode(password);
				
		User user = User.builder()
						.email(email)
						.name(name)
						.password(encodedePassword )
						.level(1L) // NotNull!
						.build();
		return userRepository.save(user);
	}

}
