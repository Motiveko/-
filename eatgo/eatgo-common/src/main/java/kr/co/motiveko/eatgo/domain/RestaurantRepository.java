package kr.co.motiveko.eatgo.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{

	
	// 이거를 자동으로 구현해준다고?
	List<Restaurant> findAllByAddressContaining(String region);

	List<Restaurant> findAll();
//	Restaurant findById(Long id); ... Optional<T> : null을 처리하지 않고 T가 있냐 없냐를 구분하는 타입이다.
	Optional<Restaurant> findById(Long id);

	Restaurant save(Restaurant restaurant);


}