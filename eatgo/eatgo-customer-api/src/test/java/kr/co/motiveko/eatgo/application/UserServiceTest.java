package kr.co.motiveko.eatgo.application;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import static org.mockito.BDDMockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.motiveko.eatgo.domain.EatgoUser;
import kr.co.motiveko.eatgo.domain.UserRepository;

public class UserServiceTest {

	
	UserService userService;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	PasswordEncoder passwordEncoder;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		userService = new 	UserService(userRepository,passwordEncoder);
		
	}
	
	@Test
	public void registerUser() {
		String email = "test@example.com";
		String name = "Tester";
		String password = "test";
		
		EatgoUser user = userService.registerUser(email,name,password);
		
		verify(userRepository).save(any());
	}
	
	@Test( expected=EmailExistedException.class )
	public void registerWithExistedEmail() {

		String email = "test@example.com";
		String name = "Tester";
		String password = "test";
		
		EatgoUser mockUser = EatgoUser.builder().build();
		given(userRepository.findByUserEmail(email)).willReturn(Optional.of(mockUser));
		
		EatgoUser user = userService.registerUser(email,name,password);
		
		verify(userRepository, never()).save(any());
	}
}
