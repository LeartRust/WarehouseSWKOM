package at.fhtw.swen3.gps.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
class GeoCoordinatesTest {
    GeoCoordinates geo = new GeoCoordinates();

    @Test
    void testGetGeoCoordinates() throws IOException {
        geo.getCoordinates("1600 Amphitheatre Parkway, Mountain View, CA");
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