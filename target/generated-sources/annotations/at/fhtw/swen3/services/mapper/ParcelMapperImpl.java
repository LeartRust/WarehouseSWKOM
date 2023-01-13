package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-12T18:57:55+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class ParcelMapperImpl implements ParcelMapper {

    private final RecipientMapper recipientMapper = RecipientMapper.INSTANCE;

    @Override
    public ParcelEntity ParcelNewParcelInfoTrackingInformationDtoToParcelEntity(Parcel parcel, NewParcelInfo newParcelInfo, TrackingInformation trackingInformation) {
        if ( parcel == null && newParcelInfo == null && trackingInformation == null ) {
            return null;
        }

        ParcelEntity.ParcelEntityBuilder parcelEntity = ParcelEntity.builder();

        if ( parcel != null ) {
            parcelEntity.weight( parcel.getWeight() );
            parcelEntity.recipient( recipientMapper.RecipientDtoToRecipientEntity( parcel.getRecipient() ) );
            parcelEntity.sender( recipientMapper.RecipientDtoToRecipientEntity( parcel.getSender() ) );
        }
        if ( newParcelInfo != null ) {
            parcelEntity.trackingId( newParcelInfo.getTrackingId() );
        }
        if ( trackingInformation != null ) {
            parcelEntity.state( trackingInformation.getState() );
            parcelEntity.visitedHops( hopArrivalListToHopArrivalEntityList( trackingInformation.getVisitedHops() ) );
            parcelEntity.futureHops( hopArrivalListToHopArrivalEntityList( trackingInformation.getFutureHops() ) );
        }

        return parcelEntity.build();
    }

    @Override
    public ParcelEntity TrackingInformationDtoToParcelEntity(TrackingInformation trackingInformation) {
        if ( trackingInformation == null ) {
            return null;
        }

        ParcelEntity.ParcelEntityBuilder parcelEntity = ParcelEntity.builder();

        parcelEntity.state( trackingInformation.getState() );
        parcelEntity.visitedHops( hopArrivalListToHopArrivalEntityList( trackingInformation.getVisitedHops() ) );
        parcelEntity.futureHops( hopArrivalListToHopArrivalEntityList( trackingInformation.getFutureHops() ) );

        return parcelEntity.build();
    }

    @Override
    public ParcelEntity ParcelDtoToParcelEntity(Parcel parcel) {
        if ( parcel == null ) {
            return null;
        }

        ParcelEntity.ParcelEntityBuilder parcelEntity = ParcelEntity.builder();

        parcelEntity.weight( parcel.getWeight() );
        parcelEntity.recipient( recipientMapper.RecipientDtoToRecipientEntity( parcel.getRecipient() ) );
        parcelEntity.sender( recipientMapper.RecipientDtoToRecipientEntity( parcel.getSender() ) );

        return parcelEntity.build();
    }

    @Override
    public ParcelEntity NewParcelInfoDtoToParcelEntity(NewParcelInfo newParcelInfo) {
        if ( newParcelInfo == null ) {
            return null;
        }

        ParcelEntity.ParcelEntityBuilder parcelEntity = ParcelEntity.builder();

        parcelEntity.trackingId( newParcelInfo.getTrackingId() );

        return parcelEntity.build();
    }

    @Override
    public ParcelEntity ParcelNewParcelInfoDtoToParcelEntity(Parcel parcel, NewParcelInfo newParcelInfo) {
        if ( parcel == null && newParcelInfo == null ) {
            return null;
        }

        ParcelEntity.ParcelEntityBuilder parcelEntity = ParcelEntity.builder();

        if ( parcel != null ) {
            parcelEntity.weight( parcel.getWeight() );
            parcelEntity.recipient( recipientMapper.RecipientDtoToRecipientEntity( parcel.getRecipient() ) );
            parcelEntity.sender( recipientMapper.RecipientDtoToRecipientEntity( parcel.getSender() ) );
        }
        if ( newParcelInfo != null ) {
            parcelEntity.trackingId( newParcelInfo.getTrackingId() );
        }

        return parcelEntity.build();
    }

    protected HopArrivalEntity hopArrivalToHopArrivalEntity(HopArrival hopArrival) {
        if ( hopArrival == null ) {
            return null;
        }

        HopArrivalEntity hopArrivalEntity = new HopArrivalEntity();

        hopArrivalEntity.setCode( hopArrival.getCode() );
        hopArrivalEntity.setDescription( hopArrival.getDescription() );
        hopArrivalEntity.setDateTime( hopArrival.getDateTime() );

        return hopArrivalEntity;
    }

    protected List<HopArrivalEntity> hopArrivalListToHopArrivalEntityList(List<HopArrival> list) {
        if ( list == null ) {
            return null;
        }

        List<HopArrivalEntity> list1 = new ArrayList<HopArrivalEntity>( list.size() );
        for ( HopArrival hopArrival : list ) {
            list1.add( hopArrivalToHopArrivalEntity( hopArrival ) );
        }

        return list1;
    }
}
