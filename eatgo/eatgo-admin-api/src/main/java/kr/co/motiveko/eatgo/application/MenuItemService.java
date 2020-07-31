package kr.co.motiveko.eatgo.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.motiveko.eatgo.domain.MenuItem;
import kr.co.motiveko.eatgo.domain.MenuItemRepository;

@Service
public class MenuItemService {


	private MenuItemRepository menuItemRepository;
	
	@Autowired	
	public MenuItemService(MenuItemRepository menuItemRepository) {
		this.menuItemRepository = menuItemRepository;
	}
	
	
	public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
		
		for(MenuItem menuItem : menuItems) {
			update(restaurantId, menuItem);			
		}
		
//		List<MenuItem> list = menuItemRepository.findAllByRestaurantId(restaurantId);
		
	}
	
	//Refactor - extract method로 추출되었다!
	private void update(Long restaurantId, MenuItem menuItem) {
		if(menuItem.isDestroy()) { 
			menuItemRepository.deleteById(menuItem.getId());
			return;
		} 
		menuItem.setRestaurantId(restaurantId);
		menuItemRepository.save(menuItem);
	}


	public List<MenuItem> getMenuItems(Long restaurantId) {

		List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(restaurantId);		
		return menuItems;
	}

}
