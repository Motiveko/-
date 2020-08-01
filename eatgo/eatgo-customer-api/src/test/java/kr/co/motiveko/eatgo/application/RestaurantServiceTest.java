package kr.co.motiveko.eatgo.application;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.motiveko.eatgo.domain.MenuItem;
import kr.co.motiveko.eatgo.domain.MenuItemRepository;
import kr.co.motiveko.eatgo.domain.Restaurant;
import kr.co.motiveko.eatgo.domain.RestaurantNotFoundException;
import kr.co.motiveko.eatgo.domain.RestaurantRepository;
import kr.co.motiveko.eatgo.domain.Review;
import kr.co.motiveko.eatgo.domain.ReviewRepository;

public class RestaurantServiceTest {

	private RestaurantService restaurantService;
	
	@Mock
	private RestaurantRepository restaurantRepository;
	
	@Mock
	private MenuItemRepository menuItemRepository;

	@Mock
	private ReviewRepository reviewRepository;
	
	
	// 이 테스트에 restauranservice 에 new로 인스턴스생성하면 스프링이 bean 만든 이후에 객채를 만드므로
	// RestaurantService 객체 안에 @Autowired 한 부분이 null이 된다.
	// 모든 테스트를 수행하기 전에 반드시 한번 실행한다.
	@Before 
	public void setUp() {
		
		// @Mock 으로 가짜를 넣어줄것이기때문에 필요없다
//		restaurantRepository = new RestaurantRepositoryImpl();
//		menuItemReposiory = new MenuItemRepositoryImpl();
		
		// @Mock, @SpyBean 등의 가짜객체 어노테이션 붙어있는것을을 initialize( instance생성?) 해준다.
		MockitoAnnotations.initMocks(this);
		mockMenuItemRepository();
		mockRestaurantRepository();
		mockReviewRepository();
		
	
		restaurantService = new RestaurantService(restaurantRepository,
													menuItemRepository,
													reviewRepository);
	}

	// given: 레포지토리는 ~를 넣으면 ~를 반환할것이다 라고 선언해주는거, @Mock은 가짜객체기때문에
	// 실제로 동작하지 않기때문에 우리가 이렇게 만들어줘야한다.
	// Repositry의 구현부는 상관없다. Service에 대해서만 테스트하므로 이런 가짜를 만든다. 기대값을 만들고 테스트한다.
	private void mockRestaurantRepository() {
		List<Restaurant> restaurants = new ArrayList<>();
		
		Restaurant restaurant = Restaurant.builder()
				.id(1004L)
				.name("Bob zip")
				.address("Seoul")
				.build();

		restaurants.add(restaurant);
		
		given(restaurantRepository.findAllByAddressContaining("Seoul")).willReturn(restaurants);
		given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
		
	}

	private void mockMenuItemRepository() {
		List<MenuItem> menuItems = new ArrayList<>();
		menuItems.add(MenuItem.builder()
				.name("Kimchi")
				.build());
		given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
	}
	
	private void mockReviewRepository() {
		List<Review> reviews = new ArrayList<>();

		reviews.add(Review.builder()
							.name("BeRyong")
							.score(5)
							.description("good")
							.build());
		given(reviewRepository.findAllByRestaurantId(1004L))
							.willReturn(reviews);
	}
	
	@Test
	public void getRestaurants() {		
		String region= "Seoul";
		List<Restaurant> restaurants = restaurantService.getRestaurants(region);
			
		Restaurant restaurant = restaurants.get(0);
		assertThat(restaurant.getId(), is(1004L)); 
	}
	
	@Test
	public void getRestaurantWithExisted() {
		
		Restaurant restaurant = restaurantService.getRestaurant(1004L);
		assertThat(restaurant.getId(), is(1004L));
		
		verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
		verify(reviewRepository).findAllByRestaurantId(eq(1004L));
		
		MenuItem menuItem = restaurant.getMenuItems().get(0);
		assertThat(menuItem.getName(), is("Kimchi"));
		
		Review review = restaurant.getReviews().get(0);
		assertThat(review.getDescription(),is("good"));
		
	}
	
	@Test(expected = RestaurantNotFoundException.class) 
	public void getRestaurantWithNotExisted() {
		// 404R
		Restaurant restaurant = restaurantService.getRestaurant(404L);
	}	
	
}
