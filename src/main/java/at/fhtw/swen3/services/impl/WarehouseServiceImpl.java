package at.fhtw.swen3.services.impl;
import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.mapper.*;
import at.fhtw.swen3.services.validation.EntityValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private final WarehouseRepository warehouseRepository;

    @Autowired
    private final TruckRepository truckRepository;

    @Autowired
    private final TransferwarehouseRepository transferwarehouseRepository;

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
    @Autowired
    private final ParcelRepository parcelRepository;
    @Autowired
    private final RecipientRepository recipientRepository;
    @Autowired
    private final HopArrivalRepository hopArrivalRepository;

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
        log.info(""+entity);
        log.info("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        log.info(""+ WarehouseMapper.INSTANCE.WarehouseEntityToWarehouseDto(entity));
        log.info("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        if (entity != null){
            //clear DB
            clearDb();
            // write to DB
            log.info("--------------------------------------------------------START--------------------------------------------------------");
            saveNextHops(entity);
            log.info("--------------------------------------------------------END--------------------------------------------------------");
            this.geoCoordinateRepository.save(entity.getLocationCoordinates());
            this.warehouseRepository.save(entity);
        }else{
            BLException exception= new BLException(2, "Import failed, check json", null);
            errorRepository.save(exception.getErrorEntity());
            throw exception;

        }
        return null;
    }

    private void saveNextHops(WarehouseEntity warehouse) {
        log.info("--------------------------------------------------------START saveNextHops--------------------------------------------------------");
        for (WarehouseNextHopsEntity nhEntity : warehouse.getNextHops()) {
            this.geoCoordinateRepository.save(nhEntity.getHop().getLocationCoordinates());
            if(nhEntity.getHop() instanceof WarehouseEntity warehouseEntity){
                log.info("--------------------------------------------------------WAREHOUSE--------------------------------------------------------");
                saveNextHops(warehouseEntity);
            }else if(nhEntity.getHop() instanceof TruckEntity truckEntity){
                log.info("--------------------------------------------------------TRUCK "+truckEntity.getCode() + "--------------------------------------------------------");
                this.truckRepository.save(truckEntity);
            }else if(nhEntity.getHop() instanceof TransferwarehouseEntity transferwarehouseEntity){
                log.info("--------------------------------------------------------TRANFERWAREHOUSE--------------------------------------------------------");
                this.transferwarehouseRepository.save(transferwarehouseEntity);
            }
            this.hopRepository.save(nhEntity.getHop());
            this.warehouseNextHopsRepository.save(nhEntity);
        }
        log.info("--------------------------------------------------------END FOR--------------------------------------------------------");
        this.geoCoordinateRepository.save(warehouse.getLocationCoordinates());
        this.warehouseRepository.save(warehouse);
        log.info("--------------------------------------------------------END saveNextHops--------------------------------------------------------");

    }

    @Override
    public Warehouse exportWarehouses() throws BLException {
        WarehouseEntity warehouseEntity = warehouseRepository.findFirstByIdIsNotNullOrderByIdAsc();


        if (warehouseEntity != null){
            Warehouse warehouseDto = WarehouseMapper.INSTANCE.WarehouseEntityToWarehouseDto(warehouseEntity);
            log.info("Export Warehouse DTO: " + warehouseDto);
            return warehouseDto;
        }else{
            BLException exception= new BLException(2, "No Warehouse found in the Database", null);
            errorRepository.save(exception.getErrorEntity());
            throw exception;
        }
    }


    private void clearDb(){

        //need to manually "delete from hop_next_hops;" WHY?
        warehouseNextHopsRepository.deleteAll();
        hopRepository.deleteAll();
        errorRepository.deleteAll();
        geoCoordinateRepository.deleteAll();
        hopArrivalRepository.deleteAll();
        parcelRepository.deleteAll();
        recipientRepository.deleteAll();
        transferwarehouseRepository.deleteAll();
        truckRepository.deleteAll();
        warehouseRepository.deleteAll();

    }

}
