package kr.co.motiveko.eatgo.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import kr.co.motiveko.eatgo.application.ReservationService;
import kr.co.motiveko.eatgo.domain.Reservation;

@RestController
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	// 가게 주인이자기 가게 번호로 가져올것이다
	@GetMapping("/reservations")
	public List<Reservation> list(Authentication authentication) {
		
		Claims claims = (Claims) authentication.getPrincipal();
		
		Long restaurantId = claims.get("restaurantId", Long.class);
		
		List<Reservation> reservations = 
				reservationService.getReservations(restaurantId);
		return reservations;
	}
}
