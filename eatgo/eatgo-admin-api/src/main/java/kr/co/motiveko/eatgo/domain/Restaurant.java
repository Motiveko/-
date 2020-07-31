package kr.co.motiveko.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;

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
public class Restaurant{
 
	@Id
	@GeneratedValue // id값 자동으로 생성
	@Setter
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String address;
	
	// @Entity에서 임시적으로 쓰는 필드에 표시. Json으로 돌려주기 위해 임시로 쓰는것이었다. MappingException 발생시 해결
	@Transient
	// Jackson의 @JsonInclude :: 이게 붙은 Entity를 Json화 시킬 때 menuItems는 NonNull일 때에만 포함시키겠다.
	// restaurant list 출력 시 menuItems는 안긁어오기때문에 항상 null로 표시되는데 이를 지워버린다!
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private List<MenuItem> menuItems;

	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Review> reviews;
	
		
	public String getInformation() {
		return name + " in " + address;
	}
	
	public void addMenuItem(MenuItem menuItem) {
		menuItems.add(menuItem);
	}	
	public void updateInformation(String name, String address) {
		this.name = name;
		this.address = address;
	}
	public void setMenuItems(List<MenuItem> menuItems) {
//		this.menuItems = menuItems; // 이렇게하면 참조만 하게 된다. 아래와 같이 새로 생성해줘야한다.
		this.menuItems = new ArrayList<>(menuItems);
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = new ArrayList<>(reviews);
		
	}

}
