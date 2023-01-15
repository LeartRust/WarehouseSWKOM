package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {WarehouseNextHopsMapper.class, GeoCoordinateMapper.class})
public interface WarehouseMapper {

    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);
    List<WarehouseEntity> WarehouseDtoListToWarehouseEntityList(List<Warehouse> warehouseDtoList);

    List<Warehouse> WarehouseEntityListToWarehouseDtoList(List<WarehouseEntity> warehouseEntityList);

    WarehouseEntity WarehouseDtoToWarehouseEntity(Warehouse warehouse);

    Warehouse WarehouseEntityToWarehouseDto(WarehouseEntity warehouseEntity);

    Warehouse HopDtoToWarehouseDto(Hop Hop);


}
