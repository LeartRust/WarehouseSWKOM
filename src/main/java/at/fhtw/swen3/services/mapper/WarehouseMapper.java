package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface WarehouseMapper {
    @Mapping(source = "warehouse.level", target = "level")
    @Mapping(source = "warehouse.nextHops", target = "nextHops")
    Warehouse from(Warehouse warehouse);
}
