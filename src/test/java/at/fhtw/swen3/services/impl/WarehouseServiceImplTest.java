package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.service.impl.GeoCoordinates;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@Transactional
@SpringBootTest
class WarehouseServiceImplTest {

    @Autowired
    WarehouseServiceImpl warehouseService;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    WarehouseNextHopsRepository warehouseNextHopsRepository;
    @Autowired
    GeoCoordinateRepository geoCoordinateRepository;
    @Autowired
    HopRepository hopRepository;
    @Autowired
    ErrorRepository errorRepository;


    @Test
    void getWarehouse() throws BLException {
        WarehouseEntity warehouse = getWarehouseEntity();
        Hop hop = warehouseService.getWarehouse(warehouse.getCode());
        assertEquals("Random Entity warehouse", hop.getLocationName());

    }

    @Test
    void importWarehouses() throws BLException {
        Warehouse warehouse = getWarehouseDto();
        warehouseService.importWarehouses(warehouse);
    }

    @Test
    void exportWarehouses() throws BLException{
        WarehouseEntity warehouseEntity = getWarehouseEntity();
        Warehouse warehouse = warehouseService.exportWarehouses();
        log.info("Exported warehouses: " + warehouse);
    }

    WarehouseEntity getWarehouseEntity() {
        GeoCoordinateEntity geoCoordinatesEntity = new GeoCoordinateEntity();
        geoCoordinateRepository.save(geoCoordinatesEntity);
        geoCoordinatesEntity.setLon(16.3774409);
        geoCoordinatesEntity.setLat(48.2391664);
        WarehouseEntity warehouse = new WarehouseEntity();
        warehouse.setLevel(2);
        warehouse.setCode(getUniqueCode());
        warehouse.setLocationName("Random Entity warehouse");
        warehouse.setLocationCoordinates(geoCoordinatesEntity);
        warehouse.setNextHops(new ArrayList<>());
        warehouseRepository.save(warehouse);

        log.info("CODEEE: " + getUniqueCode());

        return warehouse;
    }

    Warehouse getWarehouseDto(){
        GeoCoordinate geoCoordinate = new GeoCoordinate();
        geoCoordinate.setLon(16.3774409);
        geoCoordinate.setLat(48.2391664);
        Warehouse warehouse = new Warehouse();
        warehouse.locationName("Random warehouse");
        warehouse.setHopType("warehouse");
        warehouse.setDescription("random description");
        warehouse.setProcessingDelayMins(2);
        warehouse.setLevel(2);
        warehouse.setLevel(1);
        warehouse.setNextHops(new ArrayList<>());
        warehouse.setCode(getUniqueCode());
        warehouse.setLocationCoordinates(geoCoordinate);
        warehouse.setNextHops(new ArrayList<>());
        return warehouse;
    }

    public String getUniqueCode(){
        String code;
        String trackingIdCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String trackingIdNumber = "1234567890";
        do {
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 4) { // length of the random string.
                int index = (int) (rnd.nextFloat() * trackingIdCHARS.length());
                salt.append(trackingIdCHARS.charAt(index));
            }
            while (salt.length() < 8) { // length of the random string.
                int index = (int) (rnd.nextFloat() * trackingIdNumber.length());
                salt.append(trackingIdNumber.charAt(index));
            }
            code = salt.toString();
        }while (warehouseRepository.findByCode(code) != null);
        return code;
    }
}