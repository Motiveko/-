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

	public Review addReview( Long restaurantId, String name, Integer score, String description) {
		Review review = Review.builder()
							.restaurantId(restaurantId)
							.name(name)
							.score(score)
							.description(description)
							.build();
		
		return reviewRepository.save(review);
	}

}
