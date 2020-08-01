package kr.co.motiveko.eatgo.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class RegionTest {

	@Test
	public void creation() {
		
		Region region = Region.builder().name("서울").build();
		
		assertThat(region.getName(), is("서울"));
	}

}
