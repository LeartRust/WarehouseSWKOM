package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GeoCoordinateMapper {
    GeoCoordinateMapper INSTANCE = Mappers.getMapper(GeoCoordinateMapper.class);

    //@Mapping(source = "hopEntity.hopType", target = "hopType")
    GeoCoordinateEntity GeoCoordinateDtoToGeoCoordinateEntity(GeoCoordinate geoCoordinate);

    GeoCoordinate GeoCoordinateEntityToGeoCoordinateDto(GeoCoordinateEntity geoCoordinateEntity);
}
