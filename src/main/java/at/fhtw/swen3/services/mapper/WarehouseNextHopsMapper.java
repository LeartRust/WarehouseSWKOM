package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface WarehouseNextHopsMapper {
    @Mapping(source = "warehouseNextHops.traveltimeMins", target = "traveltimeMins")
    @Mapping(source = "warehouseNextHops.hop", target = "hop")
    WarehouseNextHops from(WarehouseNextHops warehouseNextHops);
}
