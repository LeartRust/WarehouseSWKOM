package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.Hop;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-14T20:21:02+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class HopMapperImpl implements HopMapper {

    private final GeoCoordinateMapper geoCoordinateMapper = GeoCoordinateMapper.INSTANCE;

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
        hop.setLocationCoordinates( geoCoordinateMapper.GeoCoordinateEntityToGeoCoordinateDto( hopEntity.getLocationCoordinates() ) );

        return hop;
    }

    @Override
    public HopEntity HopDtoToHopEntity(Hop hop) {
        if ( hop == null ) {
            return null;
        }

        HopEntity hopEntity = new HopEntity();

        hopEntity.setHopType( hop.getHopType() );
        hopEntity.setCode( hop.getCode() );
        hopEntity.setDescription( hop.getDescription() );
        hopEntity.setProcessingDelayMins( hop.getProcessingDelayMins() );
        hopEntity.setLocationName( hop.getLocationName() );
        hopEntity.setLocationCoordinates( geoCoordinateMapper.GeoCoordinateDtoToGeoCoordinateEntity( hop.getLocationCoordinates() ) );

        return hopEntity;
    }
}
