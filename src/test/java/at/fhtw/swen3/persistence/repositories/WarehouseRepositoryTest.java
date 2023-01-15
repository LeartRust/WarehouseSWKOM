package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.BaseTest;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class WarehouseRepositoryTest extends BaseTest {

    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    GeoCoordinateRepository geoCoordinateRepository;

    @Test
    void saveWarehouseEntityInDb(){
        WarehouseEntity warehouseEntity = getWarehouseEntity();
        GeoCoordinateEntity geoCoordinateEntity = warehouseEntity.getLocationCoordinates();
        geoCoordinateRepository.save(geoCoordinateEntity);
        warehouseRepository.save(warehouseEntity);
    }
    @Test
    void findByCode() {
        WarehouseEntity warehouseEntity = getWarehouseEntity();
        GeoCoordinateEntity geoCoordinateEntity = warehouseEntity.getLocationCoordinates();
        geoCoordinateRepository.save(geoCoordinateEntity);
        warehouseRepository.save(warehouseEntity);
        WarehouseEntity searchedWarehouseEntity = warehouseRepository.findByCode(warehouseEntity.getCode());

        assertEquals(warehouseEntity, searchedWarehouseEntity);

    }
}