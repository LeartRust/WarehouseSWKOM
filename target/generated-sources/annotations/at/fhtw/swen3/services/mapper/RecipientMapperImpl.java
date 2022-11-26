package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.Recipient;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-26T15:40:28+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class RecipientMapperImpl implements RecipientMapper {

    @Override
    public Recipient from(Recipient recipient) {
        if ( recipient == null ) {
            return null;
        }

        Recipient recipient1 = new Recipient();

        recipient1.setName( recipient.getName() );
        recipient1.setStreet( recipient.getStreet() );
        recipient1.setPostalCode( recipient.getPostalCode() );
        recipient1.setCity( recipient.getCity() );
        recipient1.setCountry( recipient.getCountry() );

        return recipient1;
    }
}
