package kr.co.motiveko.eatgo.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.motiveko.eatgo.application.RestaurantService;
import kr.co.motiveko.eatgo.domain.MenuItem;
import kr.co.motiveko.eatgo.domain.MenuItemRepository;
import kr.co.motiveko.eatgo.domain.Restaurant;
import kr.co.motiveko.eatgo.domain.RestaurantRepository;

@RestController 
public class RestaurantController {
	
	// Controller : Layerd Architecture 에서 UI Layer에 해당
	// Repository : Domain Layer

	@Autowired
	RestaurantService restaurantService;
	
	
	@GetMapping("/restaurants")
	public List<Restaurant> list() {
		List<Restaurant> restaurants = restaurantService.getRestaurants();
		return restaurants;
	}
	
	// 맵핑 주소에 바뀔 수 있는 값은 {id} 와 같이 중괄호로 묶어줄 수 있다.
	// 그리고 해당 값은 @PathVariable(id)로 받아줄 수 있다.
	@GetMapping("/restaurants/{id}")
	public Restaurant detail( @PathVariable("id") Long id) {
		
		Restaurant restaurant = restaurantService.getRestaurant(id);
		
//		List<MenuItem> menuItems = menuItemReposiory.findAllByRestaurantId(id);
//		restaurant.setMenuItems(menuItems);
		
		return restaurant;
	}
}
