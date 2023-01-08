package at.fhtw.swen3.gps.service;

import java.io.IOException;

public interface GeoEncodingService {
    void getCoordinates(String address) throws IOException;

    String getAddress(String lat, String lon) throws IOException;
}
