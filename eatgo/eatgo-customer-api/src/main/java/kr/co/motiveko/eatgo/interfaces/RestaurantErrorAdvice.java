package kr.co.motiveko.eatgo.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.co.motiveko.eatgo.domain.RestaurantNotFoundException;

@ControllerAdvice
public class RestaurantErrorAdvice {
	
	// .class 넣어준 종류의 Exception을 잡아준다.
	@ExceptionHandler(RestaurantNotFoundException.class)
	// 얘는 NotFound Status(404)를 돌려준다.
	@ResponseStatus(HttpStatus.NOT_FOUND)
	// return값이 Response의 body에 들어간다.
	@ResponseBody
	public String handleNotFound() {
		return "{}";
	}
}
