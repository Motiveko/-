package kr.co.motiveko.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Restaurant {

	@Id
	@GeneratedValue // id값 자동으로 생성
	private Long id;
	
	private String name;
	private String address;
	
	// 임시적으로 쓰는 필드에 표시. Json으로 돌려주기 위해 임시로 쓰는것이었다. MappingException 발생시 해결
	@Transient
	private List<MenuItem> menuItems = new ArrayList<>();
	
	public Restaurant() {
		
	}

	public Restaurant(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public Restaurant(Long id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
	

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getInformation() {
		return name + " in " + address;
	}
	
	public String getAddress() {
		return address;	
	}
	public List<MenuItem> getMenuItems(){
		return menuItems;
	}
	public void addMenuItem(MenuItem menuItem) {
		menuItems.add(menuItem);
	}	
	public void setMenuItems(List<MenuItem> menuItems) {
		for( MenuItem menuItem: menuItems) {
			addMenuItem(menuItem);
		}
	}
	public void setId(long id) {
		this.id = id;
	}

}
