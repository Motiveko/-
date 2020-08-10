package kr.co.motiveko.eatgo.interfaces;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.motiveko.eatgo.application.ReservationService;
import kr.co.motiveko.eatgo.domain.Reservation;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
	private ReservationService reservationService;
	

	@Test
	public void create() throws Exception {
		
		
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.8hm6ZOJykSINHxL-rf0yV882fApL3hyQ9-WGlJUyo2A";
		
		
		Long userId = 1004L;
		String name = "John";
		String date = "2019-12-24";
		String time = "20:00";
		Integer partySize = 20;
		
		given(reservationService.addResrvation(
				any(),any(),any(),any(),any(),any()))
				.willReturn(Reservation.builder().id(12L).build());
		mvc.perform(post("/restaurants/369/reservations")
					.contentType(MediaType.APPLICATION_JSON)
					.header("Authorization","Bearer " + token)
					.content("{\"date\":\"2019-12-24\",\"time\":\"20:00\",\"partySize\":20}"))
			.andExpect(status().isCreated());
		
		
		
		
		verify(reservationService).addResrvation(
					eq(369L),eq(userId), eq(name), eq(date), eq(time), eq(partySize));
		
	}

}
