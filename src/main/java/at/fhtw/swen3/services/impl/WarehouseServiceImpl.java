package at.fhtw.swen3.services.impl;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.HopMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseNextHopsRepository warehouseNextHopsRepository;
    private final GeoCoordinateRepository geoCoordinateRepository;
    private final HopRepository hopRepository;

    @Override
    public Hop getWarehouse(String code) throws BLException {
        HopEntity hopEntity = hopRepository.findByCode(code);
        //if(hopEntity.getHopType().equals("warehouse")){
            //WarehouseEntity warehouseEntity = warehouseRepository.findbyId(code);
        //}

        if (hopEntity != null){
            log.info("LOGGGG: " + HopMapper.INSTANCE.HopEntityToHopDto(hopEntity).getHopType());
            return HopMapper.INSTANCE.HopEntityToHopDto(hopEntity);
        }else{
            throw new BLException(2, "Parcel not found, check TrackingId", null);
        }
    }

}
