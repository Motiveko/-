package kr.co.motiveko.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/regions")
	public ResponseEntity<?> create(@RequestBody Region resource) throws URISyntaxException{
		//TODO:
		regionService.addRegion(resource.getName());
		String url = "/regions/1";
		return ResponseEntity.created(new URI(url)).body("{}");
	}
	
}
