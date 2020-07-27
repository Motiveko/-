package kr.co.motiveko.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Required;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

	@Id
	@GeneratedValue // id값 자동으로 생성
	@Setter
	private Long id;
	
	private String name;
	private String address;
	
	// @Entity에서 임시적으로 쓰는 필드에 표시. Json으로 돌려주기 위해 임시로 쓰는것이었다. MappingException 발생시 해결
	@Transient
	private List<MenuItem> menuItems;
	
		
	public String getInformation() {
		return name + " in " + address;
	}
	
	public void addMenuItem(MenuItem menuItem) {
		menuItems.add(menuItem);
	}	
	public void setMenuItems(List<MenuItem> menuItems) {
//		this.menuItems = menuItems; // 이렇게하면 참조만 하게 된다. 아래와 같이 새로 생성해줘야한다.
		this.menuItems = new ArrayList<>(menuItems);
	}
	public void updateInformation(String name, String address) {
		this.name = name;
		this.address = address;
	}
}
