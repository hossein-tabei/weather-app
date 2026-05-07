package demo.application.backend.geo.service;

import demo.application.backend.geo.GeoMapper;
import demo.application.backend.geo.data.repository.IGeoRepository;
import demo.application.backend.model.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeoServiceImpl implements GeoService {

	private final IGeoRepository iGeoRepository;
	private final GeoMapper geoMapper;

	@Override
	public List<Location> searchGeo(String searchClause) {
		return geoMapper.map(iGeoRepository.searchGeo(searchClause));
	}
}
