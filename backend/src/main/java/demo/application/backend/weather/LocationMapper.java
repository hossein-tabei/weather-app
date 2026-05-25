package demo.application.backend.weather;

import demo.application.backend.app.dto.LocationDto;
import demo.application.backend.infra.model.ApiLocation;
import demo.application.backend.weather.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mapping(target="city", source="name")
    Location map(ApiLocation apiLocation);
    List<Location> map(List<ApiLocation> list);

    List<LocationDto> mapTo(List<Location> weather);
}
