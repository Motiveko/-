package kr.co.motiveko.eatgo.interfaces;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.motiveko.eatgo.application.UserService;
import kr.co.motiveko.eatgo.domain.EatgoUser;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
	UserService userService;
	
	@Test
	public void list() throws Exception {
		List<EatgoUser> users = new ArrayList<>();	
		given(userService.getUsers()).willReturn(users);
		
		mvc.perform(get("/users"))
			.andExpect(status().isOk());
	}

	@Test
	public void create() throws Exception {
		
		String email = "admin@example.com";
		String name = "Administrator";
		
		given(userService.addUser(email, name))
			.willReturn(EatgoUser.builder().userEmail(email).userName(name).build());

		mvc.perform(post("/users")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"userEmail\":\"admin@example.com\",\"userName\":\"Administrator\"}"))
			.andExpect(status().isCreated());
		verify(userService).addUser(email,name);
	}
	
	@Test
	public void update() throws Exception {
		Long id = 1004L;
		String email = "admin@example.com";
		String name = "Administrator";
		Long level = 100L;
		
		given(userService.updateUser(id, email, name, level))
			.willReturn(EatgoUser.builder()
					.userId(id)
					.userEmail(email)
					.userName(name)
					.userLevel(level).build());
		
		mvc.perform(patch("/users/1004")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"userLevel\":100,"
							+ "\"userEmail\":\"admin@example.com\","
							+ "\"userName\":\"Administrator\"}"))
			.andExpect(status().isCreated());
		verify(userService).updateUser(eq(id),eq(email),eq(name),eq(level));
	}
	
	@Test
	
	public void deactivate() throws Exception {
				//delete()로 메소드 정의하면 RequestBuilder의 delete와 이름 같아서 애러
		mvc.perform(delete("/users/1004"))
			.andExpect(status().isOk());
		
		verify(userService).deactiveUser(any());	
	}
}
