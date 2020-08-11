package kr.co.motiveko.eatgo.application;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kr.co.motiveko.eatgo.domain.Reservation;
import kr.co.motiveko.eatgo.domain.ReservationRepository;

@Service
@Transactional
public class ReservationService {

	private ReservationRepository reservationRepository;
	
	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	public Reservation addResrvation(
			Long restaurantId,Long userId, String name, String date, String time, Integer partySize) {
	
		Reservation reservation = reservationRepository.save(Reservation.builder()
											.restaurantId(restaurantId)
											.userId(userId)
											.name(name)
											.date(date)
											.time(time)
											.partySize(partySize)
											.build());
		return reservation; 
	}

}
