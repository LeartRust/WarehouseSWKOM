package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import at.fhtw.swen3.services.dto.Truck;
import at.fhtw.swen3.services.dto.Warehouse;

public class HopMapperDeciderImpl implements HopMapper{
    private final HopMapper hopMapper;

    protected HopMapperDeciderImpl(HopMapper hopMapper){
        this.hopMapper=hopMapper;
    }

    @Override
    public Hop HopEntityToHopDto(HopEntity hopEntity){
        if(hopEntity instanceof WarehouseEntity warehouseEntity){
            return WarehouseMapper.INSTANCE.WarehouseEntityToWarehouseDto(warehouseEntity);
        }else if(hopEntity instanceof TruckEntity truckEntity){
            return TruckMapper.INSTANCE.TruckEntityToTruckDto(truckEntity);
        }else if(hopEntity instanceof TransferwarehouseEntity transferwarehouseEntity){
            return TransferwarehouseMapper.INSTANCE.TransferwarehouseEntityToTransferwarehouseDto(transferwarehouseEntity);
        }
        return hopMapper.HopEntityToHopDto(hopEntity);
    };
    @Override
    public HopEntity HopDtoToHopEntity(Hop hop){
        if(hop instanceof Warehouse warehouse){
            return WarehouseMapper.INSTANCE.WarehouseDtoToWarehouseEntity(warehouse);
        }else if(hop instanceof Truck truck){
            return TruckMapper.INSTANCE.TruckDtoToTruckEntity(truck);
        }else if(hop instanceof Transferwarehouse transferwarehouse){
            return TransferwarehouseMapper.INSTANCE.TransferwarehouseDtoToTransferwarehouseEntity(transferwarehouse);
        }
        return hopMapper.HopDtoToHopEntity(hop);
    };
}
