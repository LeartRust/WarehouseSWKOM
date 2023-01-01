package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TruckMapper {
    @Mapping(source = "truck.regionGeoJson", target = "regionGeoJson")
    @Mapping(source = "truck.numberPlate", target = "numberPlate")
    Truck from(Truck truck);
}
