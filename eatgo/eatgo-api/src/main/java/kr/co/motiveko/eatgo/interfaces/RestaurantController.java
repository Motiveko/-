package kr.co.motiveko.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.motiveko.eatgo.application.RestaurantService;
import kr.co.motiveko.eatgo.domain.MenuItem;
import kr.co.motiveko.eatgo.domain.MenuItemRepository;
import kr.co.motiveko.eatgo.domain.Restaurant;
import kr.co.motiveko.eatgo.domain.RestaurantRepository;

@CrossOrigin
@RestController 
public class RestaurantController {
	//Layerd Architecture 
	// Controller : UI (Interface, Presentational) Layer
	// Service : Application(Business) Layer?
	// Repository : Domain (Persistence) Layer

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
	
	// 가게 생성
	// POST, /restaurant, HTTP Status 201(Created), Header-Location 에 만든 정보를 담아 보낸다.
	// Client - 받은 정보를 JsonParser가 돌아가게 만든다. 없으면 Empty{} 를 돌려준다.
	// HTTPie(Postman 비슷한거)
	@PostMapping("/restaurants")
	public ResponseEntity<?> create(@RequestBody Restaurant resource) throws URISyntaxException {
		
		// https://blog.naver.com/rapa100g/222030752263 URI <-> URL
		Restaurant restaurant = restaurantService.addRestaurant(
									Restaurant.builder()
									.name(resource.getName())
									.address(resource.getAddress())
									.build());
		URI location = new URI("/restaurants/" + restaurant.getId());
		
		return ResponseEntity.created(location).body("{}");
	}
	
	@PatchMapping("/restaurants/{id}")
	public String update(@PathVariable("id") Long id,
						@RequestBody Restaurant resource) {
		String name = resource.getName();
		String address = resource.getAddress();		

		restaurantService.updateRestaurant(id, name, address);
		return "{}";
	}
	
	
}
