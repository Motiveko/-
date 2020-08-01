package kr.co.motiveko.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

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
import kr.co.motiveko.eatgo.domain.Restaurant;

@CrossOrigin
@RestController 
public class RestaurantController {
	//Layerd Architecture 
	// Controller : UI (Interface, Presentational) Layer
	// Service : Application(Business) Layer?
	// Repository : Domain (Persistence) Layer
	@Autowired
	RestaurantService restaurantService;
	
	// @RequestParam("region") -> ?region=~ 를 받을 수 있다.
	@GetMapping("/restaurants")
	public List<Restaurant> list(
			@RequestParam("region") String region) {
		List<Restaurant> restaurants = restaurantService.getRestaurants(region);
		return restaurants;
	}
	
	// 맵핑 주소에 바뀔 수 있는 값은 {id} 와 같이 중괄호로 묶어줄 수 있다.
	// 그리고 해당 값은 @PathVariable(id)로 받아줄 수 있다.
	@GetMapping("/restaurants/{id}")
	public Restaurant detail( @PathVariable("id") Long id) {
		Restaurant restaurant = restaurantService.getRestaurant(id);		
		return restaurant;
	}
	
}
