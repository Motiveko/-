package kr.co.motiveko.eatgo.interfaces;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.motiveko.eatgo.domain.Restaurant;
import kr.co.motiveko.eatgo.domain.RestaurantRepository;

@RestController 
public class RestaurantController {
	
	// Controller : Layerd Architecture 에서 UI Layer에 해당
	// Repository : Domain Layer
	
	private RestaurantRepository repository = new RestaurantRepository();

	@GetMapping("/restaurants")
	public List<Restaurant> list() {

		
		List<Restaurant> restaurants = repository.findAll();
		
		
		return restaurants;
	}
	
	// 맵핑 주소에 바뀔 수 있는 값은 {id} 와 같이 중괄호로 묶어줄 수 있다.
	// 그리고 해당 값은 @PathVariable(id)로 받아줄 수 있다.
	@GetMapping("/restaurants/{id}")
	public Restaurant detail( @PathVariable("id") Long id) {
		
		Restaurant restaurant = repository.findById(id);		
		
		return restaurant;
	}
}
