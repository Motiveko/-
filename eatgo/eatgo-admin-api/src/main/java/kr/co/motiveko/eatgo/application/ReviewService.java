package kr.co.motiveko.eatgo.application;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.motiveko.eatgo.domain.Review;
import kr.co.motiveko.eatgo.domain.ReviewRepository;

@Service
public class ReviewService {

	ReviewRepository reviewRepository;
	
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	public Review addReview(Long restaurantId,Review review) {
		review.setRestaurantId(restaurantId);
		return reviewRepository.save(review);
	}

	public List<Review> getReviews() {
		return reviewRepository.findAll();
	}

}
