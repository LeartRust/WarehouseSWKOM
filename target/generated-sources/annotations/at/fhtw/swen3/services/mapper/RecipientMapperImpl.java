package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-15T17:44:07+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class RecipientMapperImpl implements RecipientMapper {

    @Override
    public RecipientEntity RecipientDtoToRecipientEntity(Recipient recipient) {
        if ( recipient == null ) {
            return null;
        }

        RecipientEntity.RecipientEntityBuilder recipientEntity = RecipientEntity.builder();

        recipientEntity.name( recipient.getName() );
        recipientEntity.street( recipient.getStreet() );
        recipientEntity.postalCode( recipient.getPostalCode() );
        recipientEntity.city( recipient.getCity() );
        recipientEntity.country( recipient.getCountry() );

        return recipientEntity.build();
    }
}
