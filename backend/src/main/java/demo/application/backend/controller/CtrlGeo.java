package demo.application.backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.application.backend.excp.InternalException;
import demo.application.backend.model.DTOResultWrapper;
import demo.application.backend.model.Location;
import demo.application.backend.service.SrvcGeo;

@RestController
@RequestMapping("/api/geo")
public class CtrlGeo {
	private Logger logger = LoggerFactory.getLogger(CtrlGeo.class);
	
	@Autowired private SrvcGeo srvcGeo;
	
	@GetMapping(path="/search", produces= {MediaType.APPLICATION_JSON_VALUE})
	public DTOResultWrapper<List<Location>> searchGeo(@RequestParam String searchClause) throws InternalException {
		logger.trace("searchClause:{}",searchClause);
		List<Location> searchResult = this.srvcGeo.searchGeo(searchClause);
		return new DTOResultWrapper<List<Location>>("Operation Done Successfully", searchResult);
	}
}
