package demo.application.backend.geo.api.controller;

import demo.application.backend.excp.InternalException;
import demo.application.backend.model.DTOResultWrapper;
import demo.application.backend.model.Location;
import demo.application.backend.geo.service.GeoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/geo")
@Slf4j
@RequiredArgsConstructor
public class GeoController {

	private final GeoService geoService;

	@GetMapping(path="/search", produces= {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<List<Location>> searchGeo(@RequestParam String searchClause) throws InternalException {
		log.trace("searchClause:{}",searchClause);
		List<Location> searchResult = this.geoService.searchGeo(searchClause);
		return new DTOResultWrapper<>("Operation Done Successfully", searchResult);
	}
}

