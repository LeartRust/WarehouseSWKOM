package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-14T20:21:02+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class TransferwarehouseMapperImpl implements TransferwarehouseMapper {

    private final GeoCoordinateMapper geoCoordinateMapper = GeoCoordinateMapper.INSTANCE;

    @Override
    public TransferwarehouseEntity TransferwarehouseDtoToTransferwarehouseEntity(Transferwarehouse transferwarehouse) {
        if ( transferwarehouse == null ) {
            return null;
        }

        TransferwarehouseEntity transferwarehouseEntity = new TransferwarehouseEntity();

        transferwarehouseEntity.setHopType( transferwarehouse.getHopType() );
        transferwarehouseEntity.setCode( transferwarehouse.getCode() );
        transferwarehouseEntity.setDescription( transferwarehouse.getDescription() );
        transferwarehouseEntity.setProcessingDelayMins( transferwarehouse.getProcessingDelayMins() );
        transferwarehouseEntity.setLocationName( transferwarehouse.getLocationName() );
        transferwarehouseEntity.setLocationCoordinates( geoCoordinateMapper.GeoCoordinateDtoToGeoCoordinateEntity( transferwarehouse.getLocationCoordinates() ) );
        transferwarehouseEntity.setRegionGeoJson( transferwarehouse.getRegionGeoJson() );
        transferwarehouseEntity.setLogisticsPartner( transferwarehouse.getLogisticsPartner() );
        transferwarehouseEntity.setLogisticsPartnerUrl( transferwarehouse.getLogisticsPartnerUrl() );

        return transferwarehouseEntity;
    }

    @Override
    public Transferwarehouse TransferwarehouseEntityToTransferwarehouseDto(TransferwarehouseEntity transferwarehouseEntity) {
        if ( transferwarehouseEntity == null ) {
            return null;
        }

        Transferwarehouse transferwarehouse = new Transferwarehouse();

        transferwarehouse.hopType( transferwarehouseEntity.getHopType() );
        transferwarehouse.code( transferwarehouseEntity.getCode() );
        transferwarehouse.description( transferwarehouseEntity.getDescription() );
        transferwarehouse.processingDelayMins( transferwarehouseEntity.getProcessingDelayMins() );
        transferwarehouse.locationName( transferwarehouseEntity.getLocationName() );
        transferwarehouse.locationCoordinates( geoCoordinateMapper.GeoCoordinateEntityToGeoCoordinateDto( transferwarehouseEntity.getLocationCoordinates() ) );
        transferwarehouse.setRegionGeoJson( transferwarehouseEntity.getRegionGeoJson() );
        transferwarehouse.setLogisticsPartner( transferwarehouseEntity.getLogisticsPartner() );
        transferwarehouse.setLogisticsPartnerUrl( transferwarehouseEntity.getLogisticsPartnerUrl() );

        return transferwarehouse;
    }
}
