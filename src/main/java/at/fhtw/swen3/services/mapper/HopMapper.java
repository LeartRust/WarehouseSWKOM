package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.Hop;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface HopMapper {
    @Mapping(source = "hop.hopType", target = "hopType")
    @Mapping(source = "hop.code", target = "code")
    @Mapping(source = "hop.description", target = "description")
    @Mapping(source = "hop.processingDelayMins", target = "processingDelayMins")
    @Mapping(source = "hop.locationName", target = "locationName")
    @Mapping(source = "hop.locationCoordinates", target = "locationCoordinates")
    Hop from(Hop hop);
}
