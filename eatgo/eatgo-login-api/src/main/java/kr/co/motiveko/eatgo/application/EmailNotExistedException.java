package kr.co.motiveko.eatgo.application;

public class EmailNotExistedException extends RuntimeException{

	public EmailNotExistedException(String email) {
		super("Email is not registerd:" + email);
	}
	
}
