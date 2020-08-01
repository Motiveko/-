package kr.co.motiveko.eatgo.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.motiveko.eatgo.domain.Region;
import kr.co.motiveko.eatgo.domain.RegionRepository;

@Service
public class RegionService {

	@Autowired
	private RegionRepository regionRepository;

	public RegionService(RegionRepository regionRepository) {
		this.regionRepository = regionRepository;
	}
	
	public List<Region> getRegions() {
		List<Region> regions = regionRepository.findAll();
		return regions;
	}
}
