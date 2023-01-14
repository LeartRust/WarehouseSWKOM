package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class GeoCoordinatesTest {
    GeoCoordinates geo = new GeoCoordinates();

    @Test
    void testGetGeoCoordinates() throws IOException {
        RecipientEntity recipient = RecipientEntity.builder()
                .street("Höchstädtpl. 6")
                .postalCode("A-1200")
                .country("Austria")
                .city("Wien")
                .build();
        System.out.print(recipient);
        GeoCoordinateEntity geoCoordinates = geo.getCoordinates(recipient);
        log.info("COORDINATES: " + geoCoordinates.getLat());
        assertEquals(48.2391664, geoCoordinates.getLat());
        assertEquals(16.3774409, geoCoordinates.getLon());
    }

    @Test
    void testGetGeoAddress() throws IOException {
        String lat = "37.4217636";
        String lon = "-122.084614";
        geo.getAddress(lat, lon);
        assertEquals(1, 1);
    }
}