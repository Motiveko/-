package kr.co.motiveko.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import kr.co.motiveko.eatgo.application.ReservationService;
import kr.co.motiveko.eatgo.domain.Reservation;

@RestController
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@PostMapping("/restaurants/{restaurantId}/reservations")
	public ResponseEntity<?> create(
			Authentication authentication,
			@PathVariable("restaurantId") Long restaurantId
			,@Valid @RequestBody Reservation resource 
			) throws URISyntaxException {
		
		Claims claims = (Claims) authentication.getPrincipal();
		
		String name = claims.get("name", String.class);
		Long userId = claims.get("userId", Long.class);

		String date = resource.getDate();
		String time = resource.getTime();
		Integer partySize = resource.getPartySize();
		
		Reservation reservation = reservationService.addResrvation(restaurantId, userId, name, date, time, partySize);
		
		String url = "/restaurants/" + restaurantId + "/reservations/" + reservation.getId() ;
		return ResponseEntity.created(new URI(url)).body("{}");
	}
}
