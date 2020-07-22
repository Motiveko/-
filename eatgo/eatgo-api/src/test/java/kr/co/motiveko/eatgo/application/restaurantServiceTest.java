package kr.co.motiveko.eatgo.application;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.co.motiveko.eatgo.domain.MenuItem;
import kr.co.motiveko.eatgo.domain.MenuItemRepository;
import kr.co.motiveko.eatgo.domain.MenuItemRepositoryImpl;
import kr.co.motiveko.eatgo.domain.Restaurant;
import kr.co.motiveko.eatgo.domain.RestaurantRepository;
import kr.co.motiveko.eatgo.domain.RestaurantRepositoryImpl;

public class restaurantServiceTest {

	
	private RestaurantService restaurantService;
	private RestaurantRepository restaurantRepository;
	private MenuItemRepository menuItemReposiory;
	
	
	// 이 테스트에 restauranservice 에 new로 인스턴스생성하면 스프링이 bean 만든 이후에 객채를 만드므로
	// RestaurantService 객체 안에 @Autowired 한 부분이 null이 된다.
	// 모든 테스트를 수행하기 전에 반드시 한번 실행한다.
	@Before 
	public void setUp() {
		restaurantRepository = new RestaurantRepositoryImpl();
		menuItemReposiory = new MenuItemRepositoryImpl();
		restaurantService = new RestaurantService(restaurantRepository,menuItemReposiory);
	}
	
	@Test
	public void getRestaurant() {
		Restaurant restaurant = restaurantService.getRestaurant(1004L);
		assertThat(restaurant.getId(), is(1004L));
	}
	
	@Test
	public void getRestaurants() {
		Restaurant restaurant = restaurantService.getRestaurant(1004L);
		assertThat(restaurant.getId(), is(1004L)); 
		
		
		MenuItem menuItem = restaurant.getMenuItems().get(0);
		assertThat(menuItem.getName(),is("Kimchi"));
	}

}
