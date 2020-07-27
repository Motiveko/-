package kr.co.motiveko.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // Identifier로 구분되는 객체다. 관리(managed) 되는 객체
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItem {

	@Id //Identifier로 사용하겠다.
	@GeneratedValue //Id 자동으로 할당.
	private Long id;
	
	private Long restaurantId;
	
	private String name;

}
