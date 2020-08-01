package kr.co.motiveko.eatgo.application;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import kr.co.motiveko.eatgo.domain.CategoryRepository;
import kr.co.motiveko.eatgo.domain.Category;
import kr.co.motiveko.eatgo.domain.CategoryRepository;

@Service
public class CategoryServiceTest {


	private CategoryService categoryService;

	@Mock
	private CategoryRepository categoryRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		categoryService = new CategoryService(categoryRepository);	
	}

	@Test
	public void getCategory() {
		
		List<Category> mockCategories= categoryService.getCategories();
		mockCategories.add(Category.builder().name("Korean Food").build());
		given(categoryRepository.findAll()).willReturn(mockCategories);
		
		
		List<Category> categories= categoryService.getCategories();		
		Category Category = categories.get(0);
		assertThat(Category.getName(), is("Korean Food"));
	}
	
	@Test
	public void addCategory() {
		Category category = categoryService.addCategory("Korean Food");
		assertThat(category.getName(),is("Korean Food"));
	}

}
