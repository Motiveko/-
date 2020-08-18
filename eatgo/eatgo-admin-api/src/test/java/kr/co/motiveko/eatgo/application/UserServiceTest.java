package kr.co.motiveko.eatgo.application;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;

import kr.co.motiveko.eatgo.domain.EatgoUser;
import kr.co.motiveko.eatgo.domain.UserRepository;

public class UserServiceTest {

	
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;

	@Before
	public void setUp() {	
		
		MockitoAnnotations.initMocks(this);
		userService = new UserService(userRepository);
		
	}
	
	@Test
	public void getUsers() {
		List<EatgoUser> mockUsers = new ArrayList<>();
		mockUsers.add(EatgoUser.builder()
					.userEmail("test@example.com")
					.userName("motiveko")
					.userLevel(3L)
					.build());
		given(userRepository.findAll()).willReturn(mockUsers);
		
		List<EatgoUser> users = userService.getUsers();
		EatgoUser user = users.get(0);
		
		assertThat(user.getUserName(), is("motiveko"));
	}
	
	@Test
	public void addUser() {
		
		String email = "admin@example.com";
		String name = "Administrator";
				
		EatgoUser mockUser = EatgoUser.builder().userEmail(email).userName(name).build();
		
		given(userRepository.save(any())).willReturn(mockUser);
		
		EatgoUser user = userService.addUser(email, name);
		
		assertThat(user.getUserEmail(), is(email));
		assertThat(user.getUserName(), is(name));
	}
	
	@Test
	public void updateUser() {
		
		Long id = 1004L;
		String email = "admin@example.com";
		String name = "Superman";
		Long level = 100L;
		
		
		EatgoUser mockUser = EatgoUser.builder()
				.userId(id)
				.userEmail(email)
				.userName("Administrator").build();
		
		given(userRepository.findById(any())).willReturn(Optional.of(mockUser));
		
		EatgoUser user = userService.updateUser(id,email, name, level);

		verify(userRepository).findById(eq(id));

		assertThat(user.getUserName(),is("Superman"));
		assertThat(user.isAdmin(), is(true));
	}
	
	@Test
	public void deactiveUser() {
		
		Long id= 1004L;
		EatgoUser mockUser = EatgoUser.builder().userId(id).userLevel(100L).build();
		given(userRepository.findById(id)).willReturn(Optional.of(mockUser));
		
		EatgoUser user = userService.deactiveUser(id);
		verify(userRepository).findById(id);
	
		assertThat(user.isAdmin(), is(false));
		assertThat(user.isActive(), is(false));	
	}
}
