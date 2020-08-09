package kr.co.motiveko.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter // 전체 Getter안하면 test에서 .content에서 오류나는듯
@Builder
public class Review {

	@Id
	@GeneratedValue
	private Long id;
	
//	@NotEmpty
	private String name;
	
	@Setter
	private Long restaurantId;
	
	@Min(0)
	@Max(5)
	@NotNull // score는 NotEmpty가 안된다는데..
	private Integer score;
	
	@NotEmpty
	private String description;


}
