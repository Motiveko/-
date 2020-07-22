package kr.co.motiveko.eatgo.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.motiveko.eatgo.application.RestaurantService;
import kr.co.motiveko.eatgo.domain.MenuItemRepository;
import kr.co.motiveko.eatgo.domain.MenuItemRepositoryImpl;
import kr.co.motiveko.eatgo.domain.RestaurantRepository;
import kr.co.motiveko.eatgo.domain.RestaurantRepositoryImpl;


@RunWith(SpringRunner.class) // spring runner를 이용해서 테스트한다.
@WebMvcTest(RestaurantController.class) // RestaurantController에 대해서 테스트한다.
public class RestaurantControllerTest {

	@Autowired
	private MockMvc mvc;
	
	// @SpringBootApplication 에서는 controller에 의존성 주입 등이 잘 되지만 Test에서는 controller만 가져와서 안되는듯하다.
	// 이렇게 ControllerTest에 @SpyBean을 이용해 넣어주면 자동으로 찾아간다.
	@SpyBean(RestaurantRepositoryImpl.class) //interface를 @SpyBean할 때는 괄호에 구현채.class를 넣어줘야한다.
	private RestaurantRepository repository;
//	
	@SpyBean(MenuItemRepositoryImpl.class)
	private MenuItemRepository menuItemRepository;
	
	@SpyBean
	private RestaurantService restaurantService;
	
	@Test
	public void list() throws Exception {
		mvc.perform(get("/restaurants"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
			.andExpect(content().string(containsString("\"id\":1004")));
	}
	
	@Test
	public void detail() throws Exception {
		mvc.perform(get("/restaurants/1004"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
			.andExpect(content().string(containsString("\"id\":1004")))
			.andExpect(content().string(containsString("Kimchi")));
		
		mvc.perform(get("/restaurants/2020"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("\"id\":2020")))
		.andExpect(content().string(containsString("\"name\":\"Cyber Food\"")));
	}

}
