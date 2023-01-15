package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-15T17:44:07+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class ParcelMapperImpl implements ParcelMapper {

    private final RecipientMapper recipientMapper = RecipientMapper.INSTANCE;

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
    public Parcel ParcelEntityToParcelDto(ParcelEntity parcelEntity) {
        if ( parcelEntity == null ) {
            return null;
        }

        Parcel parcel = new Parcel();

        parcel.setWeight( parcelEntity.getWeight() );
        parcel.setRecipient( recipientEntityToRecipient( parcelEntity.getRecipient() ) );
        parcel.setSender( recipientEntityToRecipient( parcelEntity.getSender() ) );

        return parcel;
    }

    protected Recipient recipientEntityToRecipient(RecipientEntity recipientEntity) {
        if ( recipientEntity == null ) {
            return null;
        }

        Recipient recipient = new Recipient();

        recipient.setName( recipientEntity.getName() );
        recipient.setStreet( recipientEntity.getStreet() );
        recipient.setPostalCode( recipientEntity.getPostalCode() );
        recipient.setCity( recipientEntity.getCity() );
        recipient.setCountry( recipientEntity.getCountry() );

        return recipient;
    }
}
