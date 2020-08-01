package kr.co.motiveko.eatgo.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.motiveko.eatgo.application.CategoryService;
import kr.co.motiveko.eatgo.domain.Category;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	private CategoryService categoryService;
	
	@Test
	public void list() throws Exception {
		
		List<Category> categories = new ArrayList<>();
		categories.add(Category.builder().name("Korean Food").build());
		given(categoryService.getCategories()).willReturn(categories );
		
		mvc.perform(get("/categories"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Korean Food")));
	}
}
