package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.Parcel;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-26T15:40:28+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class ParcelMapperImpl implements ParcelMapper {

    @Override
    public Parcel from(Parcel parcel) {
        if ( parcel == null ) {
            return null;
        }

        Parcel parcel1 = new Parcel();

        parcel1.setWeight( parcel.getWeight() );
        parcel1.setRecipient( parcel.getRecipient() );
        parcel1.setSender( parcel.getSender() );

        return parcel1;
    }
}
