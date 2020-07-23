package kr.co.motiveko.eatgo.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// spring data jpa는 extends만 하고 구현은 안해도 jpa를 쓸 수 있다..
// 내가 method를 구현하지 않아도 알아서 id값과 비교해야할거 비교해서 다 가져와준다?
public interface MenuItemRepository extends CrudRepository<MenuItem, Long>{
	List<MenuItem> findAllByRestaurantId(Long restaurantId);

	
	// Persistence : 영속화, 서버가 꺼져도 데이터가 남아있게한다.
	// Hibernate : JPA의 구현체
	// @Entity : Identifier로 구분되는  객체
	
	
	
	/*
	 * Repository에는 extends CrudRepository< T, ID> 하고 interface에서 따로 구현하지는 않아도 된다.
	 * T ( == DTO, Restaurant) 에는 @Entity를 붙여 jpa가 관리가 가능하게 만들고 @Entity가 붙은 객체는 
	 * @Id 가 붙은 Identifier가 필요하다.
	 * Id는 @GeneratedValue로 자동생성이 가능하다.
	 * 
	 * DTO 객체는 Optional<T> 형식으로 반환되어야 한다.
	 * 
	 * 코드가 매우 깔끔하게 만들어진다.
	 */
	
}
