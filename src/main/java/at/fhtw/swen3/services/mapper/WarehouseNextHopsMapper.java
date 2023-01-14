package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {HopMapper.class})
public interface WarehouseNextHopsMapper {
    WarehouseNextHopsMapper INSTANCE = Mappers.getMapper(WarehouseNextHopsMapper.class);

    WarehouseNextHopsEntity WarehouseNextHopsDtoToWarehouseNextHopsEntity(WarehouseNextHops warehouseNextHops);

    WarehouseNextHops WarehouseNextHopsEntityToWarehouseNextHopsDto(WarehouseNextHopsEntity warehouseNextHopsEntity);
}
