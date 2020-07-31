package kr.co.motiveko.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.motiveko.eatgo.application.ReviewService;
import kr.co.motiveko.eatgo.domain.Review;

@RestController
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@PostMapping("/restaurants/{restaurantId}/reviews")
	public ResponseEntity<?> create(
			@PathVariable("restaurantId") Long restaurantId,
			@Valid @RequestBody Review resource) throws URISyntaxException {
			// ResposeEntity<?> : ?는 모르니까 걍 써주는거다. httpResponse의 status, body 등을 정해서 return 해 줄 수 잇따.
		
		Review review = reviewService.addReview(restaurantId,resource);
		String url = "/restaurants/" + restaurantId +
					"/reviews/" + review.getId();
		return ResponseEntity.created(new URI(url))
							 .body("{}");
	}
}

