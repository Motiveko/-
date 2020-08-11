package kr.co.motiveko.eatgo.interfaces;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.motiveko.eatgo.application.ReservationService;


@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

	@Autowired
	public MockMvc mvc;
	
	@MockBean
	private ReservationService reservationService;
	
	@Test
	public void list() throws Exception {
		
		String token = 
				"eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjIwMjAsIm5hbWUiOiJKb2huIiwicmVzdGF1cmFudElkIjoxMDA0fQ.h30V0x_Eq7Jk_b6KZRDt-92t6fD1Qyankwh5cu3Xyxs";
		
		mvc.perform(get("/reservations")
					.contentType(MediaType.APPLICATION_JSON)
					.header("Authorization", "Bearear " + token))
			.andExpect(status().isOk());
		
		verify(reservationService).getReservations(1004L);
	}

}
