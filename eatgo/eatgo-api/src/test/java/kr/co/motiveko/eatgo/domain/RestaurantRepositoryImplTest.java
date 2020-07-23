package kr.co.motiveko.eatgo.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class RestaurantRepositoryImplTest {

	// Spring JPA를 사용하면 없어질 예정이다.
	
	@Test
	public void save() {
		
		RestaurantRepository repository = new RestaurantRepositoryImpl();
		
		int oldCount = repository.findAll().size();
		
		Restaurant restaurant = new Restaurant("BeRyong", "Seoul");
		repository.save(restaurant);
		assertThat(restaurant.getId(), is(1234L));
		int newCount = repository.findAll().size();
		
		assertThat(newCount-oldCount, is(1));
	}

}
