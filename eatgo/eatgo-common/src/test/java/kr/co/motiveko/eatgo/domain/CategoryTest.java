package kr.co.motiveko.eatgo.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class CategoryTest {

	@Test
	public void creation() {
		Category category = Category.builder().name("Korean Food").build();	
		assertThat(category.getName(), is("Korean Food"));
	}

}
