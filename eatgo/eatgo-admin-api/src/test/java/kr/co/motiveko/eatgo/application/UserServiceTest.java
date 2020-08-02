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

import kr.co.motiveko.eatgo.domain.User;
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
		List<User> mockUsers = new ArrayList<>();
		mockUsers.add(User.builder()
					.email("test@example.com")
					.name("motiveko")
					.level(3L)
					.build());
		given(userRepository.findAll()).willReturn(mockUsers);
		
		List<User> users = userService.getUsers();
		User user = users.get(0);
		
		assertThat(user.getName(), is("motiveko"));
	}
	
	@Test
	public void addUser() {
		
		String email = "admin@example.com";
		String name = "Administrator";
				
		User mockUser = User.builder().email(email).name(name).build();
		
		given(userRepository.save(any())).willReturn(mockUser);
		
		User user = userService.addUser(email, name);
		
		assertThat(user.getEmail(), is(email));
		assertThat(user.getName(), is(name));
	}
	
	@Test
	public void updateUser() {
		
		Long id = 1004L;
		String email = "admin@example.com";
		String name = "Superman";
		Long level = 100L;
		
		
		User mockUser = User.builder()
				.id(id)
				.email(email)
				.name("Administrator").build();
		
		given(userRepository.findById(any())).willReturn(Optional.of(mockUser));
		
		User user = userService.updateUser(id,email, name, level);

		verify(userRepository).findById(eq(id));

		assertThat(user.getName(),is("Superman"));
		assertThat(user.isAdmin(), is(true));
	}
	
	@Test
	public void deactiveUser() {
		
		Long id= 1004L;
		User mockUser = User.builder().id(id).level(100L).build();
		given(userRepository.findById(id)).willReturn(Optional.of(mockUser));
		
		User user = userService.deactiveUser(id);
		verify(userRepository).findById(id);
	
		assertThat(user.isAdmin(), is(false));
		assertThat(user.isActive(), is(false));	
	}
}
