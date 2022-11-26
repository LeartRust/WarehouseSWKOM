package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-26T15:40:28+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class GeoCoordinateMapperImpl implements GeoCoordinateMapper {

    @Override
    public GeoCoordinate from(GeoCoordinate geoCoordinate) {
        if ( geoCoordinate == null ) {
            return null;
        }

        GeoCoordinate geoCoordinate1 = new GeoCoordinate();

        geoCoordinate1.setLat( geoCoordinate.getLat() );
        geoCoordinate1.setLon( geoCoordinate.getLon() );

        return geoCoordinate1;
    }
}
