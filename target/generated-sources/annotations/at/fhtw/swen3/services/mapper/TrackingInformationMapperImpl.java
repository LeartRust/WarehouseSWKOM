package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.TrackingInformation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-26T15:40:28+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class TrackingInformationMapperImpl implements TrackingInformationMapper {

    @Override
    public TrackingInformation from(TrackingInformation trackingInformation) {
        if ( trackingInformation == null ) {
            return null;
        }

        TrackingInformation trackingInformation1 = new TrackingInformation();

        trackingInformation1.setState( trackingInformation.getState() );
        List<HopArrival> list = trackingInformation.getVisitedHops();
        if ( list != null ) {
            trackingInformation1.setVisitedHops( new ArrayList<HopArrival>( list ) );
        }
        List<HopArrival> list1 = trackingInformation.getFutureHops();
        if ( list1 != null ) {
            trackingInformation1.setFutureHops( new ArrayList<HopArrival>( list1 ) );
        }

        return trackingInformation1;
    }
}
