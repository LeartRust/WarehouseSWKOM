package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.Truck;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-15T22:43:44+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class TruckMapperImpl implements TruckMapper {

    private final GeoCoordinateMapper geoCoordinateMapper = GeoCoordinateMapper.INSTANCE;

    @Override
    public TruckEntity TruckDtoToTruckEntity(Truck truck) {
        if ( truck == null ) {
            return null;
        }

        TruckEntity truckEntity = new TruckEntity();

        truckEntity.setHopType( truck.getHopType() );
        truckEntity.setCode( truck.getCode() );
        truckEntity.setDescription( truck.getDescription() );
        truckEntity.setProcessingDelayMins( truck.getProcessingDelayMins() );
        truckEntity.setLocationName( truck.getLocationName() );
        truckEntity.setLocationCoordinates( geoCoordinateMapper.GeoCoordinateDtoToGeoCoordinateEntity( truck.getLocationCoordinates() ) );
        truckEntity.setRegionGeoJson( truck.getRegionGeoJson() );
        truckEntity.setNumberPlate( truck.getNumberPlate() );

        return truckEntity;
    }

    @Override
    public Truck TruckEntityToTruckDto(TruckEntity truckEntity) {
        if ( truckEntity == null ) {
            return null;
        }

        Truck truck = new Truck();

        truck.hopType( truckEntity.getHopType() );
        truck.code( truckEntity.getCode() );
        truck.description( truckEntity.getDescription() );
        truck.processingDelayMins( truckEntity.getProcessingDelayMins() );
        truck.locationName( truckEntity.getLocationName() );
        truck.locationCoordinates( geoCoordinateMapper.GeoCoordinateEntityToGeoCoordinateDto( truckEntity.getLocationCoordinates() ) );
        truck.setRegionGeoJson( truckEntity.getRegionGeoJson() );
        truck.setNumberPlate( truckEntity.getNumberPlate() );

        return truck;
    }
}
