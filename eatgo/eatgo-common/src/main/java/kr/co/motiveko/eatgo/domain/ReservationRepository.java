package kr.co.motiveko.eatgo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long>{
	
	List<Reservation> findAllByRestaurantId(Long restaurantId);

	Reservation save(Reservation reservation);

}
