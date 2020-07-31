package kr.co.motiveko.eatgo.application;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.motiveko.eatgo.domain.MenuItem;
import kr.co.motiveko.eatgo.domain.MenuItemRepository;

public class MenuItemServiceTest {

	private MenuItemService menuItemService;

	@Mock
	private MenuItemRepository menuItemRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		menuItemService = new MenuItemService(menuItemRepository);
	}

	@Test
	public void bulkUpdate() {
		List<MenuItem> menuItems = new ArrayList<>();

		menuItems.add(MenuItem.builder().name("Kimchi").build());
		menuItems.add(MenuItem.builder().id(12L).name("Gukbob").build());
		menuItems.add(MenuItem.builder().id(1004L).destroy(true).build());

		menuItemService.bulkUpdate(1L, menuItems);
		// times(x) : x번 실행한다.
		verify(menuItemRepository, times(2)).save(any());
		verify(menuItemRepository, times(1)).deleteById(eq(1004L));
	}
}
