package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GeoCoordinateMapper {
    @Mapping(source = "geoCoordinate.lat", target = "lat")
    @Mapping(source = "geoCoordinate.lon", target = "lon")
    GeoCoordinate from(GeoCoordinate geoCoordinate);
}
