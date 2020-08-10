package kr.co.motiveko.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

	@Id
	@GeneratedValue
	private Long id;
	
	private Long restaurantId;
	//userId와 name은 token에서 받아서 넣어줄것이다.
	
	private Long userId;
	
	private String name;
	
	@NotEmpty
	private String date;
	
	@NotEmpty
	private String time;
	
	// Reservation의 디테일은 각자구현하자!
	
	@NotNull
	@Min(1)
	@Max(15)
	private Integer partySize;
	
	
}
