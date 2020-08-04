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
	
	}
	
	@Test
	public void accessTokenWithPassword() {
		User user = User.builder().password("ACCESSTOKEN").build();
		assertThat(user.getAccessToken(), is("ACCESSTOKE"));
	}
	
	@Test
	public void accessTokenWithoutPassword() {
		User user = User.builder().build();
		assertThat(user.getAccessToken(), is(""));	
	}	

}
