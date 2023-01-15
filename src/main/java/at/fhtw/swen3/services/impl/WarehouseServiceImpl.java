package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.HopMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.validation.EntityValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

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
        //E. Get a Hop (Warehouse, Truck, TransferWarehouse) by code
        //TODO Sollen untergeordnete Hops auch angezeigt werden?

        HopEntity hopEntity = hopRepository.findByCode(code);

        if (hopEntity != null){
            return HopMapper.INSTANCE.HopEntityToHopDto(hopEntity);
        }else{
            BLException exception= new BLException(2, "Warehouse not found, check Code", null);
            errorRepository.save(exception.getErrorEntity());
            throw exception;
        }
    }

    @Override
    public Void importWarehouses(Warehouse warehouse) throws BLException {
        //C. Import a hierarchy of Hops (Warehouse, Truck, TransferWarehouse) objects.

        validator.validate(warehouse);
        WarehouseEntity entity = WarehouseMapper.INSTANCE.WarehouseDtoToWarehouseEntity(warehouse);
        log.info("----------------------------------------------------------------DTO THAT SHOULD BE IMPORTED--------------------------------------------------------------------------------------------------");
        log.info(""+ WarehouseMapper.INSTANCE.WarehouseEntityToWarehouseDto(entity));
        log.info("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        if (entity != null){
            //clear DB
            clearDb();
            // write to DB
            saveNextHops(entity);
            this.geoCoordinateRepository.save(entity.getLocationCoordinates());
            this.warehouseRepository.save(entity);
            log.info("--------------------------------------------------------IMPORT SUCCESSFUL--------------------------------------------------------");
        }else{
            BLException exception= new BLException(2, "Import failed, check json", null);
            errorRepository.save(exception.getErrorEntity());
            throw exception;
        }
        return null;
    }

    private void saveNextHops(WarehouseEntity warehouse) {
        //Methode zum impotieren/erkennen ob Warehouse/Truck/Tranferwarehouse

        log.info("--------------------------------------------------------START saveNextHops--------------------------------------------------------");
        for (WarehouseNextHopsEntity nhEntity : warehouse.getNextHops()) {
            this.geoCoordinateRepository.save(nhEntity.getHop().getLocationCoordinates());
            if(nhEntity.getHop() instanceof WarehouseEntity){
                WarehouseEntity warehouseEntity = (WarehouseEntity) nhEntity.getHop();
                log.info("--------------------------------------------------------WAREHOUSE--------------------------------------------------------");
                saveNextHops(warehouseEntity);
            }else if(nhEntity.getHop() instanceof TruckEntity){
                TruckEntity truckEntity = (TruckEntity) nhEntity.getHop();
                log.info("--------------------------------------------------------TRUCK "+truckEntity.getCode() + "--------------------------------------------------------");
                this.truckRepository.save(truckEntity);
            }else if(nhEntity.getHop() instanceof TransferwarehouseEntity){
                TransferwarehouseEntity transferwarehouseEntity = (TransferwarehouseEntity) nhEntity.getHop();
                log.info("--------------------------------------------------------TRANSFERWAREHOUSE--------------------------------------------------------");
                this.transferwarehouseRepository.save(transferwarehouseEntity);
            }
            this.hopRepository.save(nhEntity.getHop());
            this.warehouseNextHopsRepository.save(nhEntity);
        }
        log.info("--------------------------------------------------------END FOR LOOP--------------------------------------------------------");
        this.geoCoordinateRepository.save(warehouse.getLocationCoordinates());
        this.warehouseRepository.save(warehouse);
        log.info("--------------------------------------------------------END saveNextHops--------------------------------------------------------");

    }

    @Override
    public Warehouse exportWarehouses() throws BLException {
        //D. Export a hierarchy of Hops (Warehouse, Truck, TransferWarehouse) objects .

        WarehouseEntity warehouseEntity = warehouseRepository.findFirstByIdIsNotNullOrderByIdDesc();


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
        //Löschte alle Daten aus der Datenbank
        //TODO need to manually "delete from hop_next_hops;" WHY?

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
