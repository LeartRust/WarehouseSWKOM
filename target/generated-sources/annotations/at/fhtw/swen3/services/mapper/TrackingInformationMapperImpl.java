package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.TrackingInformation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-15T23:13:09+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class TrackingInformationMapperImpl implements TrackingInformationMapper {

    @Override
    public TrackingInformation ParcelEntityToTrackingInformationDto(ParcelEntity parcelEntity) {
        if ( parcelEntity == null ) {
            return null;
        }

        TrackingInformation trackingInformation = new TrackingInformation();

        trackingInformation.setState( parcelEntity.getState() );
        trackingInformation.setVisitedHops( hopArrivalEntityListToHopArrivalList( parcelEntity.getVisitedHops() ) );
        trackingInformation.setFutureHops( hopArrivalEntityListToHopArrivalList( parcelEntity.getFutureHops() ) );

        return trackingInformation;
    }

    protected HopArrival hopArrivalEntityToHopArrival(HopArrivalEntity hopArrivalEntity) {
        if ( hopArrivalEntity == null ) {
            return null;
        }

        HopArrival hopArrival = new HopArrival();

        hopArrival.setCode( hopArrivalEntity.getCode() );
        hopArrival.setDescription( hopArrivalEntity.getDescription() );
        hopArrival.setDateTime( hopArrivalEntity.getDateTime() );

        return hopArrival;
    }

    protected List<HopArrival> hopArrivalEntityListToHopArrivalList(List<HopArrivalEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<HopArrival> list1 = new ArrayList<HopArrival>( list.size() );
        for ( HopArrivalEntity hopArrivalEntity : list ) {
            list1.add( hopArrivalEntityToHopArrival( hopArrivalEntity ) );
        }

        return list1;
    }
}
