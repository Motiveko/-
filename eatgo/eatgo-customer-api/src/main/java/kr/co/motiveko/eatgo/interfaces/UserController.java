package kr.co.motiveko.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.motiveko.eatgo.application.UserService;
import kr.co.motiveko.eatgo.domain.User;
import kr.co.motiveko.eatgo.utils.JwtUtil;

@RestController
@CrossOrigin
public class UserController {

	// Email, Name, Password
	// 201
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/users")
	public ResponseEntity<?> postMethodName( @RequestBody User resource) 
			throws URISyntaxException {
		System.out.println(resource);
		String email = resource.getEmail();
		String name = resource.getName();
		String password = resource.getPassword();

		
		User user = userService.registerUser(email,name,password);
		String url = "/users/"+user.getId();
	
		return ResponseEntity.created(new URI(url)).body("{}");
	}

	
}
