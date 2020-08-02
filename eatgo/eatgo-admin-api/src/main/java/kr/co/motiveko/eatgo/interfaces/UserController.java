package kr.co.motiveko.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.motiveko.eatgo.application.UserService;
import kr.co.motiveko.eatgo.domain.User;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> users(){
		
		List<User> users = userService.getUsers();
		return users;
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {
		
		String email = resource.getEmail();
		String name = resource.getName();
		
		User user = userService.addUser(email, name);
		String url = "/users/"+user.getId();
		return ResponseEntity.created(new URI(url)).body("{}");
	}
	
	@PatchMapping("/users/{userId}")
	public ResponseEntity<?> updateUser(
			@PathVariable("userId")Long id,
			@RequestBody User resource) throws URISyntaxException {
		
		String email = resource.getEmail();
		String name = resource.getName();
		Long level = resource.getLevel();
				
		userService.updateUser(id, email, name, level);
		
		String url = "/users/"+id;
		
		return ResponseEntity.created(new URI(url)).body("{}");
	}
	
	@DeleteMapping("/users/{userId}")
	public void delete(@PathVariable("userId")Long id) {
		userService.deactiveUser(id);
	}

	
}
