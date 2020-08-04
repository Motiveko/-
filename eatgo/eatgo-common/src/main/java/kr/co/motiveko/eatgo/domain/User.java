package kr.co.motiveko.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String name;
	
	@NotNull
	private Long level;
	
	private String password;

	public boolean isAdmin() {
		return level>=3;
	}

	public boolean isActive() {
		return level>0;
	}

	public void deactive() {
		level = 0L;
	}

	// Json으로 주지 말자 필요없으니까!
	@JsonIgnore 
	public String getAccessToken() {
		if(password==null) return "";
		return password.substring(0,10);
	}

}
