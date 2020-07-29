package kr.co.motiveko.eatgo.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.motiveko.eatgo.application.ReviewService;

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
		
		reviewService.addReview(review);
		
		//
		verify(ReviewRepository).save(review);
	}

}
