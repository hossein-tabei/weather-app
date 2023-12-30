package demo.application.backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.application.backend.api.IRepoGeo;
import demo.application.backend.converter.ApiToDtoConverter;
import demo.application.backend.model.Location;

@Service
public class SrvcGeo {
	private Logger logger = LoggerFactory.getLogger(SrvcGeo.class);
	
	@Autowired
	IRepoGeo repoGeo;
	
	public SrvcGeo(IRepoGeo repoGeo) {
		logger.trace("repoGeo type:{}",repoGeo.getClass().toString());
	}
	
	public List<Location> searchGeo(String searchClause) {
		logger.trace("searchClause:{}",searchClause);
		return ApiToDtoConverter.convertApiLocationListToLocationList(repoGeo.searchGeo(searchClause));
	}
}
