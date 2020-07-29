package kr.co.motiveko.eatgo.application;

import org.springframework.stereotype.Service;

import kr.co.motiveko.eatgo.domain.Review;
import kr.co.motiveko.eatgo.domain.ReviewRepository;

@Service
public class ReviewService {

	ReviewRepository reviewRepository;
	
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	public void addReview(Review review) {
		reviewRepository.save(review);
	}

}
