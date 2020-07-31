package kr.co.motiveko.eatgo.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
	public void list() throws Exception {
		
		List<Review> reviews = new ArrayList<>();
		
		reviews.add(Review.builder().description("Cool!").build());
		given(reviewService.getReviews()).willReturn(reviews);
		
		
		// 관리자는 그냥 전체 리뷰에 대한 테스트만 할 것이므로 /restaurants/{id}/..가 아닌 /reviews로 간다.
		mvc.perform(get("/reviews"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Cool!")));
	}
}
