package kr.co.motiveko.eatgo.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.motiveko.eatgo.application.MenuItemService;
import kr.co.motiveko.eatgo.domain.MenuItem;

@RestController
public class MenuItemController {

	
	@Autowired
	MenuItemService menuItemrService;
	
	@PatchMapping("/restaurants/{restaurantId}/menuitems")
	public String bulkUpdate(
			@PathVariable("restaurantId") Long restaurantId,
			@RequestBody List<MenuItem> menuItems) {
		
		menuItemrService.bulkUpdate(restaurantId,menuItems);
		return "";
	}
}
