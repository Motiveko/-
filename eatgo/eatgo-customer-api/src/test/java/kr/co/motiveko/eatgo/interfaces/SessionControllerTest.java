package kr.co.motiveko.eatgo.interfaces;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

import kr.co.motiveko.eatgo.application.EmailNotExistedException;
import kr.co.motiveko.eatgo.application.PasswordWrongException;
import kr.co.motiveko.eatgo.application.UserService;
import kr.co.motiveko.eatgo.domain.User;


@RunWith(SpringRunner.class) // spring runner를 이용해서 테스트한다.
@WebMvcTest(SessionController.class) // RestaurantController에 대해서 테스트한다.
public class SessionControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
	private UserService userService;
	
	
	// authentication Valid
	@Test
	public void createWithValidAttributes() throws Exception {
		
		String email = "tester@example.com";
		String password = "test";
		
		User mockUser = User.builder().password("ACCESSTOKEN").build();
		given(userService.authenticate(email, password)).willReturn(mockUser);
		
		mvc.perform(post("/session")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"email\":\"tester@example.com\",\"name\":\"Tester\",\"password\":\"test\"}"))
			.andExpect(status().isCreated())
			.andExpect(header().string("location","/session"))
			.andExpect(content().string("{\"accessToken\":\"ACCESSTOKE\"}"));
	
		verify(userService).authenticate(eq(email),eq(password));
	}	
	
	
	// 이메일 없는 케이스
	@Test
	public void createWithNotExistedEmail() throws Exception {
		given(userService.authenticate("x", "test"))
		.willThrow(EmailNotExistedException.class);
		
		
		mvc.perform(post("/session")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"x\",\"name\":\"Tester\",\"password\":\"test\"}"))
		.andExpect(status().isBadRequest());
		
		
		verify(userService).authenticate(eq("x"),eq("test"));
	}		
	//비밀번호 틀린 케이스
	@Test
	public void createWithWrongPassword() throws Exception {
		given(userService.authenticate("tester@example.com", "x"))
			.willThrow(PasswordWrongException.class);

		
		mvc.perform(post("/session")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"email\":\"tester@example.com\",\"name\":\"Tester\",\"password\":\"x\"}"))
			.andExpect(status().isBadRequest());
		
	
		verify(userService).authenticate(eq("tester@example.com"),eq("x"));
	}		
}