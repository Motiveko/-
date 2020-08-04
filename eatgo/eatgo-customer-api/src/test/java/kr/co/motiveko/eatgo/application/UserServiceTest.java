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

import kr.co.motiveko.eatgo.domain.User;
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

	//올바른 authentication
	@Test
	public void authenticateWithValidAttributes() {
		
		String email = "test@example.com";
		String password = "test";		

		
		User mockUser = User.builder().email(email).build();
		given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser ));
		given(passwordEncoder.matches(any(),any())).willReturn(true);
		
		User user = userService.authenticate(email, password);
		
		assertThat(user.getEmail(), is(email));		
	}

	// 이메일 없는 authentication
	@Test(expected = EmailNotExistedException.class)
	public void authenticateWithNotExistedEmail() {
		
		String email = "x";
		String password = "test";		
		
		given(userRepository.findByEmail(email)).willReturn(Optional.empty());
		
		userService.authenticate(email, password);
	}
	
	// 비번 틀린 authentication
	@Test(expected = PasswordWrongException.class)
	public void authenticateWithWrongPassword() {
		
		String email = "test@example.com";
		String password = "test";		
		
		User mockUser = User.builder().email(email).build();
		given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
		given(passwordEncoder.matches(any(),any())).willReturn(false);
		
//		given(passwordEncoder.matches(password, encodedPassword))
		
		userService.authenticate(email, password);
	}
}
