package kr.co.motiveko.eatgo.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.motiveko.eatgo.domain.Category;
import kr.co.motiveko.eatgo.domain.CategoryRepository;

@Service
public class CategoryService {	

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<Category> getCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

	public Category addCategory(String name) {
		Category category = Category.builder().name(name).build();
		categoryRepository.save(category);
		return category;
	}
	

}