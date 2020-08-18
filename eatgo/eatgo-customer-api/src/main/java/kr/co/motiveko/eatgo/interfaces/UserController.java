package kr.co.motiveko.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.motiveko.eatgo.application.UserService;
import kr.co.motiveko.eatgo.domain.EatgoUser;
import kr.co.motiveko.eatgo.utils.JwtUtil;

@RestController
public class UserController {

	// Email, Name, Password
	// 201
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/users")
	public ResponseEntity<?> postMethodName(@RequestBody EatgoUser resource) 
			throws URISyntaxException {
		
		String email = resource.getUserEmail();
		String name = resource.getUserName();
		String password = resource.getUserPassword();	
		
		EatgoUser user = userService.registerUser(email,name,password);
		String url = "/users/"+user.getUserId();
	
		return ResponseEntity.created(new URI(url)).body("{}");
	}

	
}
