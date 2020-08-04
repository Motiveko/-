package kr.co.motiveko.eatgo.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.motiveko.eatgo.application.ReviewService;
import kr.co.motiveko.eatgo.domain.Review;

@RestController
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/reviews")
	public List<Review> list(){
		
		List<Review> reviews = reviewService.getReviews();
		return reviews;
	}
	

}

