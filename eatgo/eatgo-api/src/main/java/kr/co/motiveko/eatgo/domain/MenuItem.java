package kr.co.motiveko.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // Identifier로 구분되는 객체다. 관리(managed) 되는 객체
public class MenuItem {

	@Id //Identifier로 사용하겠다.
	@GeneratedValue //Id 자동으로 할당.
	private Long id;
	
	private Long restaurantId;
	
	private final String name;
	
	public MenuItem(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
