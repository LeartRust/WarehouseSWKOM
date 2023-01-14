package at.fhtw.swen3.services.impl;
import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.HopMapper;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.validation.EntityValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private final WarehouseRepository warehouseRepository;

    @Autowired
    private final WarehouseNextHopsRepository warehouseNextHopsRepository;

    @Autowired
    private final GeoCoordinateRepository geoCoordinateRepository;

    @Autowired
    private final HopRepository hopRepository;

    @Autowired

    private final ErrorRepository errorRepository;

    @Autowired
    private final EntityValidator validator;

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
            BLException exception= new BLException(2, "Warehouse not found, check Code", null);
            errorRepository.save(exception.getErrorEntity());
            throw exception;
        }
    }

    @Override
    public Void importWarehouses(Warehouse warehouse) throws BLException {
        validator.validate(warehouse);
        WarehouseEntity entity = WarehouseMapper.INSTANCE.WarehouseDtoToWarehouseEntity(warehouse);

        if (entity != null){
            // write to DB
            for (WarehouseNextHopsEntity nhEntity : entity.getNextHops()) {
                this.geoCoordinateRepository.save(nhEntity.getHop().getLocationCoordinates());
                this.hopRepository.save(nhEntity.getHop());
                this.warehouseNextHopsRepository.save(nhEntity);
            }
            this.geoCoordinateRepository.save(entity.getLocationCoordinates());
            this.warehouseRepository.save(entity);
        }else{
            BLException exception= new BLException(2, "Import failed, check json", null);
            errorRepository.save(exception.getErrorEntity());
            throw exception;

        }
        return null;
    }

    @Override
    public Warehouse exportWarehouses() throws BLException {
        WarehouseEntity warehouseEntity = warehouseRepository.findFirstByIdIsNotNullOrderByIdAsc();

        Warehouse warehouseDto = WarehouseMapper.INSTANCE.WarehouseEntityToWarehouseDto(warehouseEntity);
        if (warehouseEntity != null){
            log.info("Export Warehouse DTO: " + warehouseDto);
            return warehouseDto;
        }else{
            BLException exception= new BLException(2, "No Wwarehouse found in the Database", null);
            errorRepository.save(exception.getErrorEntity());
            throw exception;
        }
    }

}
