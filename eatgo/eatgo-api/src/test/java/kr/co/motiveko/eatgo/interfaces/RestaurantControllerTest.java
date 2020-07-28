package kr.co.motiveko.eatgo.interfaces;


import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.motiveko.eatgo.application.RestaurantService;
import kr.co.motiveko.eatgo.domain.MenuItem;
import kr.co.motiveko.eatgo.domain.Restaurant;
import kr.co.motiveko.eatgo.domain.RestaurantNotFoundException;


@RunWith(SpringRunner.class) // spring runner를 이용해서 테스트한다.
@WebMvcTest(RestaurantController.class) // RestaurantController에 대해서 테스트한다.
public class RestaurantControllerTest {

	@Autowired
	private MockMvc mvc;
		
	// Mock Object : 가짜 객체
	// Mockito : Mock Object Framework
	// 가짜객체이므로 안에 di된 repository를 실제루 사용하지 않으므로 안넣어줘도 된다.
	@MockBean 
	private RestaurantService restaurantService; 
	
	@Test
	public void list() throws Exception {		
		// 이렇게 직접 가짜프로세스를 만들어 준 대로 테스트를 하게 되는데 그 이유는
		// Controller가 Servie 객체를 잘 활용 한다는것을 테스트 하는 것이지 (Controller 객체 그 자체의 기능) 
		// Service객체가 잘 동작하는지의 여부가 관심사가 아니기 때문이다.
		// 실제 서버가 돌아가며 서비스하고 있는 중에 테스트를 해야 한다고 할 때 이렇게 가짜로 만들어서 테스트 해야민 한다.
		List<Restaurant> restaurants = new ArrayList<>();
		restaurants.add(Restaurant.builder()
				.id(1004L)
				.name("Bob zip")
				.address("Seoul")
				.build());
		
		// given() : static method from org.mockito.BDDMockito
		given(restaurantService.getRestaurants()).willReturn(restaurants);
		
		restaurantService.getRestaurants();
		
		mvc.perform(get("/restaurants"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
			.andExpect(content().string(containsString("\"id\":1004")));
	}
	
	@Test
	public void detailWithExisted() throws Exception {
		
		Restaurant restaurant1 = Restaurant.builder()
				.id(1004L)
				.name("Bob zip")
				.address("Seoul")
				.build();
		restaurant1.setMenuItems( Arrays.asList(MenuItem.builder()
												.name("Kimchi")
												.build()));
		given(restaurantService.getRestaurant(1004L)).willReturn(restaurant1);

		Restaurant restaurant2 =Restaurant.builder()
				.id(2020L)
				.name("Cyber Food")
				.address("Seoul")
				.build();
		given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);
		
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
	
	@Test
	public void detailWithNotExisted() throws Exception {
		// 404 Not Found
		given(restaurantService.getRestaurant(404L))
			.willThrow(new RestaurantNotFoundException(404L)); //exception을 직접 만들었다
		mvc.perform(get("/restaurants/404"))
			.andExpect(status().isNotFound())
			.andExpect(content().string("{}"));
	}	
	
	@Test
	public void createWithValidData() throws Exception {
		
		// .will과 invocation... 강의에서 뭔가 누락된부분인데 잘 모르겠다!
		given(restaurantService.addRestaurant(any())).will(invocation ->{
			Restaurant restaurant = invocation.getArgument(0);
			return Restaurant.builder()
					.id(1234L)
					.name(restaurant.getName())
					.address(restaurant.getAddress())
					.build();
		});

		mvc.perform(post("/restaurants")
					.contentType(MediaType.APPLICATION_JSON)				  // json 타입이냐
					.content("{\"name\":\"BeRyong\",\"address\":\"Busan\"}")) //
			.andExpect(status().isCreated())
			.andExpect(header().string("location", "/restaurants/1234"))
			.andExpect(content().string("{}"));

		//요것이 한번 실행되는지 검사, any()는 아무거나 들어와도 된다의 표시
		verify(restaurantService).addRestaurant(any());
	}
	
	@Test
	public void createWithInvalidData() throws Exception {
		// validation 검사에서 탈락했을때
		
		mvc.perform(post("/restaurants")
					.contentType(MediaType.APPLICATION_JSON)		
					.content("{\"name\":\"\",\"address\":\"\"}")) // Empty인자가 들어왔을때 Status : 400
			.andExpect(status().isBadRequest());

	}
	
	
	@Test
	public void updateWithValidData() throws Exception {
		mvc.perform(patch("/restaurants/1004")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"JOKER Bar\",\"address\":\"Busan\"}"))
			.andExpect(status().isOk());
		
		verify(restaurantService).updateRestaurant(1004L,"JOKER Bar","Busan");
	}
	
	@Test
	public void updateWithoutName() throws Exception {
		mvc.perform(patch("/restaurants/1004")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"\",\"address\":\"Busan\"}"))
			.andExpect(status().isBadRequest());
		
	}
}
