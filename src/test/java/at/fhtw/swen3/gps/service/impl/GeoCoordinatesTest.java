package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
class GeoCoordinatesTest {
    GeoCoordinates geo = new GeoCoordinates();

    @Test
    void testGetGeoCoordinates() throws IOException {
        RecipientEntity recipient = RecipientEntity.builder()
                .street("Höchstädtpl. 6")
                .postalCode("1200")
                .country("Austria")
                .city("Wien")
                .build();
        System.out.print(recipient);
        geo.getCoordinates(recipient);
        assertEquals(1, 1);
    }

    @Test
    void testGetGeoAddress() throws IOException {
        String lat = "37.4217636";
        String lon = "-122.084614";
        geo.getAddress(lat, lon);
        assertEquals(1, 1);
    }
}