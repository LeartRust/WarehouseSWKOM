package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Hop;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-13T19:15:39+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class HopMapperImpl implements HopMapper {

    @Override
    public Hop HopEntityToHopDto(HopEntity hopEntity) {
        if ( hopEntity == null ) {
            return null;
        }

        Hop hop = new Hop();

        hop.setHopType( hopEntity.getHopType() );
        hop.setCode( hopEntity.getCode() );
        hop.setDescription( hopEntity.getDescription() );
        hop.setProcessingDelayMins( hopEntity.getProcessingDelayMins() );
        hop.setLocationName( hopEntity.getLocationName() );
        hop.setLocationCoordinates( geoCoordinateEntityToGeoCoordinate( hopEntity.getLocationCoordinates() ) );

        return hop;
    }

    protected GeoCoordinate geoCoordinateEntityToGeoCoordinate(GeoCoordinateEntity geoCoordinateEntity) {
        if ( geoCoordinateEntity == null ) {
            return null;
        }

        GeoCoordinate geoCoordinate = new GeoCoordinate();

        geoCoordinate.setLat( geoCoordinateEntity.getLat() );
        geoCoordinate.setLon( geoCoordinateEntity.getLon() );

        return geoCoordinate;
    }
}
