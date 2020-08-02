package kr.co.motiveko.eatgo.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import static org.mockito.BDDMockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.motiveko.eatgo.domain.User;
import kr.co.motiveko.eatgo.domain.UserRepository;

public class UserServiceTest {

	
	UserService userService;
	
	@Mock
	UserRepository userRepository;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		userService = new 	UserService(userRepository);
	}
	
	@Test
	public void registerUser() {
		String email = "test@example.com";
		String name = "Tester";
		String password = "test";
		
		User user = userService.registerUser(email,name,password);
		
		verify(userRepository).save(any());
	}
	
	@Test( expected=EmailExistedException.class )
	public void registerWithExistedEmail() {

		String email = "test@example.com";
		String name = "Tester";
		String password = "test";
		
		User mockUser = User.builder().build();
		given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
		
		User user = userService.registerUser(email,name,password);
		
		verify(userRepository, never()).save(any());
	}
}
