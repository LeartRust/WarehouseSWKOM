package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.service.impl.GeoCoordinates;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.BLException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
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
        warehouseService.getWarehouse(warehouse.getCode());
    }

    @Test
    void importWarehouses() {
    }

    @Test
    void exportWarehouses() {
    }

    WarehouseEntity getWarehouseEntity() {
        GeoCoordinateEntity geoCoordinatesEntity = new GeoCoordinateEntity();
        geoCoordinateRepository.save(geoCoordinatesEntity);
        geoCoordinatesEntity.setLon(16.3774409);
        geoCoordinatesEntity.setLat(48.2391664);
        WarehouseEntity warehouse = new WarehouseEntity();
        warehouse.setLevel(2);
        warehouse.setCode("LKJH1234");
        warehouse.setLocationCoordinates(geoCoordinatesEntity);
        warehouse.setNextHops(new ArrayList<>());
        warehouseRepository.save(warehouse);
        return warehouse;
    }
}