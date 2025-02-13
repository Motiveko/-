package kr.co.motiveko.eatgo.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.motiveko.eatgo.application.RegionService;
import kr.co.motiveko.eatgo.domain.Region;


@RunWith(SpringRunner.class)
@WebMvcTest(RegionController.class)
public class RegionControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	private RegionService regionService;
	
	@Test
	public void list() throws Exception {
		
		List<Region> regions = new ArrayList<>();
		regions.add(Region.builder().name("Seoul").build());
		given(regionService.getRegions()).willReturn(regions );
		
		mvc.perform(get("/regions"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Seoul")));
	}
	
	@Test
	public void create() throws Exception {
		
		given(regionService.addRegion("Seoul"))
		.willReturn(Region.builder().name("Seoul").build());
		
		mvc.perform(post("/regions")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"Seoul\"}"))
			.andExpect(status().isCreated())
			.andExpect(content().string("{}"));
		
		verify(regionService).addRegion("Seoul");
	}
}
