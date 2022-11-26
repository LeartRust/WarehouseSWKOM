package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.HopArrival;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-26T15:40:28+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class HopArrivalMapperImpl implements HopArrivalMapper {

    @Override
    public HopArrival from(HopArrival hopArrival) {
        if ( hopArrival == null ) {
            return null;
        }

        HopArrival hopArrival1 = new HopArrival();

        hopArrival1.setCode( hopArrival.getCode() );
        hopArrival1.setDescription( hopArrival.getDescription() );
        hopArrival1.setDateTime( hopArrival.getDateTime() );

        return hopArrival1;
    }
}
