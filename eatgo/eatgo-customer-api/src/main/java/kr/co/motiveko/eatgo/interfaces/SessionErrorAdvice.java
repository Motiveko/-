package kr.co.motiveko.eatgo.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.co.motiveko.eatgo.application.EmailNotExistedException;
import kr.co.motiveko.eatgo.application.PasswordWrongException;

@ControllerAdvice
public class SessionErrorAdvice {
	//세션 애러 처리를 위한 객체
	
	// 비밀번호 틀렸다.
	@ExceptionHandler(PasswordWrongException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handlePasswordWrong() {
		return "{}";
	}
	
	//이메일이 존재하지 않는다.
	@ExceptionHandler(EmailNotExistedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleEmailNotExisted() {
		return "{}";
	}
	
}
