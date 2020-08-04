package kr.co.motiveko.eatgo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

	@SuppressWarnings("unchecked")
	Review save(Review review);

	List<Review> findAll();
	
	List<Review> findAllByRestaurantId(long eq);
}
