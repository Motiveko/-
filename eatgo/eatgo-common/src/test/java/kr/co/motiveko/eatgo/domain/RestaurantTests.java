package kr.co.motiveko.eatgo.domain;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RestaurantTests {

	@Test
	public void creation() {
		
//		Restaurant restaurant = new Restaurant(1004L ,"Bob zip","Seoul");
		
		// Design Patterns - Builder Pattern ,,, Lombok의 @Builder 붙이면 바로가능
		// arg가 많고 안쓰는것도 있는것
		Restaurant restaurant = Restaurant.builder()
				.id(1004L)
				.name("Bob zip")
				.address("Seoul")
				.build();

		// assertThat(a,is(b)) -> a=b여야한다.
		assertThat(restaurant.getId(), is(1004L)); 
		assertThat(restaurant.getName(), is("Bob zip")); 
		assertThat(restaurant.getAddress(), is("Seoul"));
	}
	
	@Test
	public void information() {
		
		Restaurant restaurant = Restaurant.builder()
				.id(1004L)
				.name("Bob zip")
				.address("Seoul")
				.build();
		
		
		assertThat(restaurant.getInformation(), is("Bob zip in Seoul"));
	}

}
