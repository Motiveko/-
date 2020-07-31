package kr.co.motiveko.eatgo.application;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.motiveko.eatgo.domain.Review;
import kr.co.motiveko.eatgo.domain.ReviewRepository;

public class ReviewServiceTest {

	private ReviewService reviewService;

	@Mock
	private ReviewRepository ReviewRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		reviewService = new ReviewService(ReviewRepository);
	}

	@Test
	public void addReview() {
		
		Review review = Review.builder()
								.name("motiveko")
								.score(3)
								.description("good")
								.build();
		
		reviewService.addReview(1004L,review);
		
		//
		verify(ReviewRepository).save(review);
	}

}
