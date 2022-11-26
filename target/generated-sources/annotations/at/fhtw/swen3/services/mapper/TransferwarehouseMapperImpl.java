package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.Transferwarehouse;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-26T15:40:28+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class TransferwarehouseMapperImpl implements TransferwarehouseMapper {

    @Override
    public Transferwarehouse from(Transferwarehouse transferwarehouse) {
        if ( transferwarehouse == null ) {
            return null;
        }

        Transferwarehouse transferwarehouse1 = new Transferwarehouse();

        transferwarehouse1.setRegionGeoJson( transferwarehouse.getRegionGeoJson() );
        transferwarehouse1.setLogisticsPartner( transferwarehouse.getLogisticsPartner() );
        transferwarehouse1.setLogisticsPartnerUrl( transferwarehouse.getLogisticsPartnerUrl() );
        transferwarehouse1.hopType( transferwarehouse.getHopType() );
        transferwarehouse1.code( transferwarehouse.getCode() );
        transferwarehouse1.description( transferwarehouse.getDescription() );
        transferwarehouse1.processingDelayMins( transferwarehouse.getProcessingDelayMins() );
        transferwarehouse1.locationName( transferwarehouse.getLocationName() );
        transferwarehouse1.locationCoordinates( transferwarehouse.getLocationCoordinates() );

        return transferwarehouse1;
    }
}
