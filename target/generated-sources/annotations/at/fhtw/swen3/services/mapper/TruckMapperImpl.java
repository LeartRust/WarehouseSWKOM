package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.Truck;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-26T15:40:28+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class TruckMapperImpl implements TruckMapper {

    @Override
    public Truck from(Truck truck) {
        if ( truck == null ) {
            return null;
        }

        Truck truck1 = new Truck();

        truck1.setRegionGeoJson( truck.getRegionGeoJson() );
        truck1.setNumberPlate( truck.getNumberPlate() );
        truck1.hopType( truck.getHopType() );
        truck1.code( truck.getCode() );
        truck1.description( truck.getDescription() );
        truck1.processingDelayMins( truck.getProcessingDelayMins() );
        truck1.locationName( truck.getLocationName() );
        truck1.locationCoordinates( truck.getLocationCoordinates() );

        return truck1;
    }
}
