package kr.co.motiveko.eatgo.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<EatgoUser, Long> {
	List<EatgoUser> findAll();

	Optional<EatgoUser> findById(Long userId);
	
	Optional<EatgoUser> findByUserEmail(String email); 
}
