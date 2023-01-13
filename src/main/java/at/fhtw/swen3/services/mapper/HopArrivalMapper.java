package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HopArrivalMapper {
    HopArrivalMapper INSTANCE = Mappers.getMapper(HopArrivalMapper.class);
    List<HopArrival> HopArrivalEntityListToHopArrivalDtoList(List<HopArrivalEntity> hopArrivalEntity);
}
