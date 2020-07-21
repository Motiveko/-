package kr.co.motiveko.eatgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication 가 붙은 MainClass가 자신의 디렉토리 하위의 모든 class들을 scan해서 @Component 종류들을 Scan해서 Bean으로 등록/관리 하게된다.ㅣ
// 만약 이 MainClass 하위 디렉토리가 아닌 애들을 Scan하려면
//@SpringBootApplication(scanBasePackages={"com.example.something", "com.example.application"}) 와 같이 해주면 된다. 
//이 때 자기 디렉토리까지 다시 써줘야함
@SpringBootApplication
public class EatgoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatgoApplication.class, args);
	}

}
