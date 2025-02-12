package kr.co.motiveko.eatgo.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.motiveko.eatgo.domain.MenuItem;
import kr.co.motiveko.eatgo.domain.MenuItemRepository;
import kr.co.motiveko.eatgo.domain.Restaurant;
import kr.co.motiveko.eatgo.domain.RestaurantNotFoundException;
import kr.co.motiveko.eatgo.domain.RestaurantRepository;
import kr.co.motiveko.eatgo.domain.Review;
import kr.co.motiveko.eatgo.domain.ReviewRepository;

@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	

	
	// @Autowired 한걸 왜 이렇게 했는지 기억이 나질 않는다.. --> Testclass에서 repository는 autowired되지 않기때문에 테스트하려면 이런식으로 넣어줘야한다.
	// 이 생성자는 테스트클래스에서 쓰려고하는거나 다름없다!
	public RestaurantService(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	public Restaurant getRestaurant(Long id) {
		
		Restaurant restaurant = restaurantRepository.findById(id).
													orElseThrow(() -> new RestaurantNotFoundException(id));
																// Supplier<T> 는 위와 같이 람다식으로 표현하면 같은 의미이다..
																// orElseThrow에 들어올때만 이것을 실행하겠다는 의미라는데.. 잘모르겠다.
						
		return restaurant;
	}

	public List<Restaurant> getRestaurants() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		return restaurants;
	}

	public Restaurant addRestaurant(Restaurant restaurant) {		
		return restaurantRepository.save(restaurant);
	}

	// @Transactional : 이 메소드를 하나의 트랜잭션으로 적용한다. 이 적용 범위를 벗어날 때 commit이 된다
	// 따로 명시적으로 restaurantRepository.save()를 할 필요가 없어진다
	@Transactional
	public Restaurant updateRestaurant(Long id, String name, String address) {
		Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
		
		// setName, setAddress를 updateInfo로 통합하여 불필요한 메소드를 줄였다.
		restaurant.updateInformation(name,address);
		return restaurant;
	}
}
