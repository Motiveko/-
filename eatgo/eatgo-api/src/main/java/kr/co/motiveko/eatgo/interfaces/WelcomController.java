package kr.co.motiveko.eatgo.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomController {
	
		@GetMapping("/") //Get방식으로 여기에 접근할 수 있다.
		public String hello() {
			return "Hello, World!!!";
		}
	
}
