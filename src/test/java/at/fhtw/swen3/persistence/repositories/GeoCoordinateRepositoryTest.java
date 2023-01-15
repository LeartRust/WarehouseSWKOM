package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.BaseTest;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GeoCoordinateRepositoryTest extends BaseTest {

    @Autowired
    GeoCoordinateRepository geoCoordinateRepository;

    @Test
    void saveGeoCoordinatesInDb(){
        GeoCoordinateEntity geoEnt = getGeoCoordinateEntity();
        geoCoordinateRepository.save(geoEnt);
    }

    @Test
    void deleteGeoCoordinatesInDb(){
        GeoCoordinateEntity geoEnt = getGeoCoordinateEntity();
        geoCoordinateRepository.save(geoEnt);
        geoCoordinateRepository.delete(geoEnt);
    }

}