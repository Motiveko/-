package kr.co.motiveko.eatgo.interfaces;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.motiveko.eatgo.application.UserService;
import kr.co.motiveko.eatgo.domain.User;


@RunWith(SpringRunner.class) // spring runner를 이용해서 테스트한다.
@WebMvcTest(UserController.class) // RestaurantController에 대해서 테스트한다.
public class UserControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void createWithValidAttributes() throws Exception {
		
		// 나는 변수선언해서 했다. 아샬은 그냥 다써준다;
		String email = "test@example.com";
		String name = "Tester";
		String password = "test";
		User mockUser = User.builder()
						.id(1004L)
						.email(email)
						.name(name)
						.password(password)
						.build();				
		given(userService.registerUser(email,name,password))
				.willReturn(mockUser);
		
		
		mvc.perform(post("/users")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"email\":\"test@example.com\",\"name\":\"Tester\",\"password\":\"test\"}"))
			.andExpect(status().isCreated())
			.andExpect(header().string("location","/users/1004"));
	
		verify(userService).registerUser(eq(email), any(), any());
	}	
}