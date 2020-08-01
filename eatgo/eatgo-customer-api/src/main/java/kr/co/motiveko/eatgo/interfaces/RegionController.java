package kr.co.motiveko.eatgo.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.motiveko.eatgo.application.RegionService;
import kr.co.motiveko.eatgo.domain.Region;

@RestController
public class RegionController {

	@Autowired
	private RegionService regionService;
	
	@GetMapping("/regions")
	public List<Region> list(){
		List<Region> regions = regionService.getRegions();
		return regions;
	}
	
}