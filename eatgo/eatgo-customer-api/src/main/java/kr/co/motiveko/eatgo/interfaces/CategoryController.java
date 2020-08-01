package kr.co.motiveko.eatgo.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.motiveko.eatgo.application.CategoryService;
import kr.co.motiveko.eatgo.domain.Category;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public List<Category> list(){
		List<Category> categories = categoryService.getCategories();
		return categories;
	}
	
}