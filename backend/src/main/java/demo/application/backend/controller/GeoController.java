package demo.application.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.application.backend.model.Location;
import demo.application.backend.repository.GeoRepository;

@RestController
@RequestMapping("/api/geo")
public class GeoController {
	
	@Autowired private GeoRepository geoRepository;
	
	@GetMapping(path="/search", produces= {MediaType.APPLICATION_JSON_VALUE})
	public List<Location> searchGeo(@RequestParam String searchClause) {
		return this.geoRepository.searchGeo(searchClause);
	}
}
