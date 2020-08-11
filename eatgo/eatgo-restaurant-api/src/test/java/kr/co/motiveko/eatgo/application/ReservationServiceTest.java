package kr.co.motiveko.eatgo.application;

import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.motiveko.eatgo.domain.Reservation;
import kr.co.motiveko.eatgo.domain.ReservationRepository;

public class ReservationServiceTest {

	private ReservationService reservationService;
	
	@Mock
	ReservationRepository reservationRepository;
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		reservationService = new ReservationService(reservationRepository);
	}
	
	@Test
	public void getReservations() {
		
		Long restaurantId = 1004L;
		List<Reservation> reservations = reservationService.getReservations(restaurantId );
		
		verify(reservationRepository).findAllByRestaurantId(restaurantId);
	}

}
