package kr.co.motiveko.eatgo.interfaces;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.motiveko.eatgo.application.MenuItemService;

@RunWith(SpringRunner.class)
@WebMvcTest( MenuItemController.class)
public class MenuItemControllerTest {

	@Autowired
	private MockMvc mvc;
			
	@MockBean
	private MenuItemService menuItemService;
	
	@Test
	public void bulkUpdate() throws Exception {
		
		mvc.perform(patch("/restaurants/1/menuitems")
				.contentType(MediaType.APPLICATION_JSON)
				.content("[]"))
		.andExpect(status().isOk());

									// mockito의 matchers를 인자로 쓸 때는 전부다 matchers로 이루어져야한다.
									// 1L, any()로 쓰면 해당 exception이 나오므로 eq(1L)을 해줘야한다.
		verify(menuItemService).bulkUpdate(eq(1L), any());
	}
	
}
