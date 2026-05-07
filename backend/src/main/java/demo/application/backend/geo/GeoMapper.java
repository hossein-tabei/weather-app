package demo.application.backend.geo;

import demo.application.backend.geo.data.model.ApiLocation;
import demo.application.backend.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeoMapper {

    @Mapping(target="city", source="name")
    Location map(ApiLocation apiCurrentStatus);
    List<Location> map(List<ApiLocation> list);
}
