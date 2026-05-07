package demo.application.backend.geo.api.controller;

import demo.application.backend.geo.service.GeoServiceImpl;
import demo.application.backend.model.DTOResultWrapper;
import demo.application.backend.model.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/geo")
@RequiredArgsConstructor
public class GeoController {

	private static final String SUCCESSFUL_MESSAGE = "Operation Done Successfully";
	private final GeoServiceImpl geoService;

	@GetMapping(path="/search", produces= {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<List<Location>> searchGeo(@RequestParam String searchClause) {
		List<Location> searchResult = this.geoService.searchGeo(searchClause);
		return new DTOResultWrapper<>(SUCCESSFUL_MESSAGE, searchResult);
	}
}

