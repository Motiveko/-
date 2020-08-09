package kr.co.motiveko.eatgo.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UserTest {

	@Test
	public void createion() {
		
		User user = User.builder()
					.email("test@example.com")
					.name("motiveko")
					.level(3L)
					.build();
		assertThat(user.getName(),is("motiveko"));
		assertThat(user.isAdmin(), is(true));
		assertThat(user.isActive(), is(true));

		user.deactive();
		
		assertThat(user.isActive(), is(false));
	}
	
	@Test
	public void restaurantOwner() {
		User user = User.builder()
						.level(1L)
						.build();
		assertThat(user.isRestaurantOwner(), is(false)); 
		
		user.setRestaurantId(1004L);
		
		assertThat(user.isRestaurantOwner(), is(true)); 	
		assertThat(user.getRestaurantId(), is(1004L));
	}

}
