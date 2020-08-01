package kr.co.motiveko.eatgo.application;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;
import org.springframework.stereotype.Service;

import kr.co.motiveko.eatgo.domain.Region;
import kr.co.motiveko.eatgo.domain.RegionRepository;

@Service
public class RegionServiceTest {


	private RegionService regionService;

	@Mock
	private RegionRepository regionRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		regionService = new RegionService(regionRepository);	
	}

	@Test
	public void getRegion() {
		
		List<Region> mockRegions= regionService.getRegions();
		mockRegions.add(Region.builder().name("Seoul").build());
		given(regionRepository.findAll()).willReturn(mockRegions);
		
		
		List<Region> regions= regionService.getRegions();		
		Region region = regions.get(0);
		assertThat(region.getName(), is("Seoul"));
	}
	
	@Test
	public void addRegion() {
		Region region = regionService.addRegion("Seoul");
		assertThat(region.getName(),is("Seoul"));
	}

}
