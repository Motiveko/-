package kr.co.motiveko.eatgo.interfaces;

import lombok.Builder;
import lombok.Data;

@Data //순수한 데이터이다
@Builder
public class SessionResponseDto {

	private String accessToken;
	
}
