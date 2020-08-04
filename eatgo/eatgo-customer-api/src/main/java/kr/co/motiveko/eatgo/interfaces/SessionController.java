package kr.co.motiveko.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.motiveko.eatgo.application.UserService;
import kr.co.motiveko.eatgo.domain.User;

@RestController
public class SessionController {

	@Autowired
	private UserService userService;

	@PostMapping("/session")						//Session Request/Response Dto 만들예정
	public ResponseEntity<SessionResponseDto> create(@RequestBody SessionRequestDto resource) throws URISyntaxException{
		
		// request된 정보에 대해 Authentication
		String email = resource.getEmail();
		String password = resource.getPassword();
		User user = userService.authenticate(email, password);
		
		// encoded password의 앞에 10자리
		String accessToken = user.getAccessToken();
		String url = "/session";
		
		// Dto를 만들어서 body에 dto를 넣어주면 자동으로 key:value의 json형식으로 바꿔준다.		
		return ResponseEntity.created(new URI(url)).body(
				SessionResponseDto.builder()
								.accessToken(accessToken)
								.build());
	}

}
