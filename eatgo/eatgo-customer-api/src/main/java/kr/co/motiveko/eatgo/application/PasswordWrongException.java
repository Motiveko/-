package kr.co.motiveko.eatgo.application;

public class PasswordWrongException extends RuntimeException {

	PasswordWrongException () {
		super("password is wrong");
	}
}
