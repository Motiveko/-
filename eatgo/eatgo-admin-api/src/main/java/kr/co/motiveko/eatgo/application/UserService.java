package kr.co.motiveko.eatgo.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.motiveko.eatgo.domain.EatgoUser;
import kr.co.motiveko.eatgo.domain.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public List<EatgoUser> getUsers(){
		List<EatgoUser> users = userRepository.findAll();
		return users;
	}
	public EatgoUser addUser(String email, String name) {
		EatgoUser user = EatgoUser.builder()
				.userEmail(email)
				.userName(name)
				.userLevel(1L) // 기본값으로 1!
				.build();
		return userRepository.save(user);		
	}
	

	public EatgoUser updateUser(Long id,String email, String name,Long level) {
		//TODO: restuaantService의 예외 참고
		EatgoUser user = userRepository.findById(id).orElse(null);
		
		//@Transactional이 자동으로 업데이트 해 줄 것이다.
		user.setUserEmail(email);
		user.setUserName(name);
		user.setUserLevel(level);
	
		return user;
	}
	public EatgoUser deactiveUser(Long id) {
		//TODO: 레벨0으로!
		// TODO null처리!
		EatgoUser user = userRepository.findById(id).orElse(null);
		user.deactive();
		return user;
	}
	

}
