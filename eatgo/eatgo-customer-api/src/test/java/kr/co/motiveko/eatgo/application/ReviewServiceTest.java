package kr.co.motiveko.eatgo.application;

import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.*;

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
		reviewService.addReview(1004L,"motiveko",3,"good");
		verify(ReviewRepository).save(any());
	}

}