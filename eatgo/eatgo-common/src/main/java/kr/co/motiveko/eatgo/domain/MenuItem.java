package kr.co.motiveko.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Identifier로 구분되는 객체다. 관리(managed) 되는 객체
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItem {

	@Id //Identifier로 사용하겠다.
	@GeneratedValue //Id 자동으로 할당.
	private Long id;
		
	private Long restaurantId;
	
	private String name;

	@Transient // db에 넣지 않을것이다. 메뉴 업데이트냐 삭제냐 여부 판단하는애다
	// Lombok에서 boolean은 Getter를 isDestroy() 이런식으로 만드는거같다.
	// default : false이므로 false일 때 포함안시킴
	@JsonInclude(JsonInclude.Include.NON_DEFAULT) 
	private boolean destroy;
}
