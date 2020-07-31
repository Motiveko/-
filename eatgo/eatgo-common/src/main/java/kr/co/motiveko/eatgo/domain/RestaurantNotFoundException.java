package kr.co.motiveko.eatgo.domain;

public class RestaurantNotFoundException extends RuntimeException {

	public RestaurantNotFoundException(long id) {
		super("Could Not Find Restaurant " + id);
	}

}
