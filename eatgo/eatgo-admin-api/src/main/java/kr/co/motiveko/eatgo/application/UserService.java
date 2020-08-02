package kr.co.motiveko.eatgo.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kr.co.motiveko.eatgo.domain.User;
import kr.co.motiveko.eatgo.domain.UserRepository;

@Service
@Transactional
public class UserService {

	UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public List<User> getUsers(){
		List<User> users = userRepository.findAll();
		return users;
	}
	public User addUser(String email, String name) {
		User user = User.builder()
				.email(email)
				.name(name)
				.level(1L) // 기본값으로 1!
				.build();
		return userRepository.save(user);		
	}
	public User updateUser(Long id,String email, String name,Long level) {
		//TODO: restuaantService의 예외 참고
		User user = userRepository.findById(id).orElse(null);
		
		//@Transactional이 자동으로 업데이트 해 줄 것이다.
		user.setEmail(email);
		user.setName(name);
		user.setLevel(level);
	
		return user;
	}
	public User deactiveUser(Long id) {
		//TODO: 레벨0으로!
		// TODO null처리!
		User user = userRepository.findById(id).orElse(null);
		user.deactive();
		return user;
	}
	

}
