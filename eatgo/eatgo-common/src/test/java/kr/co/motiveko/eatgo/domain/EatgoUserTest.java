package kr.co.motiveko.eatgo.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class EatgoUserTest {

	@Test
	public void createion() {
		
		EatgoUser user = EatgoUser.builder()
					.userEmail("test@example.com")
					.userName("motiveko")
					.userLevel(3L)
					.build();
		assertThat(user.getUserName(),is("motiveko"));
		assertThat(user.isAdmin(), is(true));
		assertThat(user.isActive(), is(true));

		user.deactive();
		
		assertThat(user.isActive(), is(false));
	}
	
	@Test
	public void restaurantOwner() {
		EatgoUser user = EatgoUser.builder()
						.userLevel(1L)
						.build();
		assertThat(user.isRestaurantOwner(), is(false)); 
		
		user.setRestaurantId(1004L);
		
		assertThat(user.isRestaurantOwner(), is(true)); 	
		assertThat(user.getRestaurantId(), is(1004L));
	}

}
