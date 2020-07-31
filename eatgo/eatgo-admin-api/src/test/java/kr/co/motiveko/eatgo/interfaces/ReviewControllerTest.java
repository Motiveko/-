package kr.co.motiveko.eatgo.interfaces;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.motiveko.eatgo.application.ReviewService;
import kr.co.motiveko.eatgo.domain.Review;


@RunWith(SpringRunner.class) // spring runner를 이용해서 테스트한다.
@WebMvcTest(ReviewController.class) // RestaurantController에 대해서 테스트한다.
public class ReviewControllerTest {

	@Autowired
	MockMvc mvc;
	
	// @Mock : 가짜 객체로 행위에 대해 return값등을 지정해줘야한다..? 또 init을 해줘야한다..?
	// @MockBean : We can use the @MockBean to add mock objects to the Spring application context. 
	@MockBean 
	private ReviewService reviewService;

	@Test
	public void createWithValidAttributes() throws Exception {
				
		given(reviewService.addReview(eq(1L),any())).willReturn(
				Review.builder()
				.id(1004L)
				.build()				
		);
		
		mvc.perform(post("/restaurants/1/reviews")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"motiveko\",\"score\":3,\"description\":\"good\"}"))
			.andExpect(status().isCreated())
			.andExpect(header().string("location","/restaurants/1/reviews/1004"));
	
		verify(reviewService).addReview(eq(1L),any());
	}
	
	@Test
	public void createWithInvalidAttributes() throws Exception {
		mvc.perform(post("/restaurants/1/reviews")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"\",\"score\":3,\"description\":\"\"}"))
			.andExpect(status().isBadRequest());
		verify(reviewService,never()).addReview(eq(1L),any());
	}	
}
