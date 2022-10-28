package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.HopArrival;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface HopArrivalMapper {
    @Mapping(source = "hopArrival.code", target = "code")
    @Mapping(source = "hopArrival.description", target = "description")
    @Mapping(source = "hopArrival.dateTime", target = "dateTime")
    HopArrival from(HopArrival hopArrival);
}
