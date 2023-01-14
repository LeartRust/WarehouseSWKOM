package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
@Slf4j
public class GeoCoordinates implements GeoEncodingService {
    //Adressen zu koordinaten
    //https://nominatim.org/release-docs/latest/api/Search/
    //&format=json
    //nominatim api

    String format = "&format=json";

    public GeoCoordinateEntity getCoordinates(RecipientEntity recipient) throws IOException {

        String originalString = recipient.getPostalCode();
        String[] parts = originalString.split("A-");
        String postalCode = parts[1];

        String address = recipient.getStreet() + " " +  recipient.getCity() + " " + recipient.getCountry() +  " " + postalCode;
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        String urlString = "https://nominatim.openstreetmap.org/search?format=json&limit=1&q=" + encodedAddress + format;
        System.out.println(encodedAddress);

        log.info("-----------");
        // Make the HTTP GET request to the Nominatim API
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response from the Nominatim API
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            System.out.println(inputLine);
        }
        in.close();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(String.valueOf(response));

        JsonNode element = root.get(0);

        Double lat = Double.valueOf(element.path("lat").textValue());
        Double lon = Double.valueOf(element.path("lon").textValue());
        System.out.println("-----------");
        System.out.println(lat);
        System.out.println(lon);

        GeoCoordinateEntity geoCoordinates = new GeoCoordinateEntity();
        geoCoordinates.setLon(lat);
        geoCoordinates.setLon(lon);

        // Print the JSON response
        System.out.println(response.toString());
        return geoCoordinates;
    }

    public String getAddress( String lat, String lon) throws IOException {

        String urlString = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" + lat + "&lon=" + lon +format;
        // Make the HTTP GET request to the Nominatim API
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response from the Nominatim API
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(String.valueOf(response));

        JsonNode element = root.get("address");

        String road = element.path("road").textValue();
        String postcode = element.path("postcode").textValue();
        String city = element.path("city").textValue();
        String country = element.path("country").textValue();

        System.out.println(road + " " +postcode + " " +city + " " + country);

        // Print the JSON response
        System.out.println(response.toString());
        return "";
    }


}
