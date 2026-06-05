package demo.application.backend;

import demo.application.backend.infra.model.ApiLocation;
import demo.application.backend.weather.model.Location;

import java.util.Map;

public class LocationTestHelper {

    public static ApiLocation getApiLocation(String name, Map<String, String> local_names, Double lat, Double lon,
            String country, String state) {
        ApiLocation obj = new ApiLocation();
        obj.setName(name);
        obj.setLocal_names(local_names);
        obj.setLat(lat);
        obj.setLon(lon);
        obj.setCountry(country);
        obj.setState(state);
        return obj;
    }

    public static Location getLocation(Double lat, Double lon, String city, String state, String country) {
        return new Location(lat, lon, city, state, country);
    }

}
