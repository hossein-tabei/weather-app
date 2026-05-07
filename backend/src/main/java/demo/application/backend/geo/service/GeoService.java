package demo.application.backend.geo.service;

import demo.application.backend.excp.InternalException;
import demo.application.backend.geo.GeoMapper;
import demo.application.backend.geo.data.repository.IGeoRepository;
import demo.application.backend.model.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeoService {

	private final IGeoRepository iGeoRepository;
	private final GeoMapper geoMapper;
	
	public List<Location> searchGeo(String searchClause) throws InternalException {
		log.trace("searchClause:{}",searchClause);
		return geoMapper.map(iGeoRepository.searchGeo(searchClause));
	}
}
