package kr.co.motiveko.eatgo.application;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.motiveko.eatgo.domain.Reservation;
import kr.co.motiveko.eatgo.domain.ReservationRepository;

@Service
public class ReservationService {

	private ReservationRepository reservationRepository;
	
	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	public List<Reservation> getReservations(Long restaurantId) {

		return reservationRepository.findAllByRestaurantId(restaurantId);
	}


}
