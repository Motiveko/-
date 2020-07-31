package kr.co.motiveko.eatgo.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.motiveko.eatgo.domain.Review;
import kr.co.motiveko.eatgo.domain.ReviewRepository;

public class ReviewServiceTest {

	private ReviewService reviewService;

	@Mock
	private ReviewRepository reviewRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		reviewService = new ReviewService(reviewRepository);
	}
	
	@Test
	public void getReviews() {
		//mock
		List<Review> mockReviews = new ArrayList<>();
		mockReviews.add(Review.builder().description("Cool!").build());
		given(reviewRepository.findAll()).willReturn(mockReviews);
		
		//real
		List<Review> reviews = reviewService.getReviews();
		assertThat(reviews.get(0).getDescription(), is("Cool!"));
	}
}
