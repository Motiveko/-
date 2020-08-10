package kr.co.motiveko.eatgo.application;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.motiveko.eatgo.domain.Reservation;
import kr.co.motiveko.eatgo.domain.ReservationRepository;

public class ReservationServiceTest {

	
	private ReservationService reservationService;
	
	@Mock
	private ReservationRepository reservationRepository;
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		reservationService = new ReservationService(reservationRepository);
		
	
	}
	
	@Test
	public void addReservation() {

		Long restaurantId = 369L;
		Long userId = 1004L;
		String name = "John";
		String date = "2019-12-24";
		String time = "20:00";
		Integer partySize = 20;
		

		given(reservationRepository.save(any())).will( invocation -> {
			// save했더니 나온걸 그대로 되돌려준다. willReturn보다 훨씬 와닿네..?
			Reservation reservation = invocation.getArgument(0);
			return reservation;
		});
		Reservation reservation = reservationService.addResrvation(
					restaurantId, userId, name, date, time, partySize);
	
		assertThat(reservation.getName(), is(name));
		
		verify(reservationRepository).save(any(Reservation.class));
	
	}

}
