package at.fhtw.swen3.gps.service;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;

import java.io.IOException;

public interface GeoEncodingService {
    GeoCoordinateEntity getCoordinates(RecipientEntity recipient) throws IOException;

    String getAddress(String lat, String lon) throws IOException;
}
