package kr.co.motiveko.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

//이 클래스를 스프링에서 관리한다.
@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

	private List<Restaurant> restaurants = new ArrayList<>();
	
	public RestaurantRepositoryImpl() {
		restaurants.add(new Restaurant(1004L ,"Bob zip","Seoul"));
		restaurants.add(new Restaurant(2020L ,"Cyber Food","Seoul"));
	}
		
	@Override
	public List<Restaurant> findAll() {
		return restaurants;
	}
	
	@Override
	public Restaurant findById(Long id) {
		Restaurant restaurant = restaurants.stream() // Stream<Restaurant>
										.filter( r -> r.getId().equals(id)) // Stream<Restaurant>
										.findFirst()	//Optional<Restaurant>
										.orElse(null); // Restaurant
														// get() : 객체반환, orElse(a) : empty일 경우 a반환
		return restaurant;
	}	
}
