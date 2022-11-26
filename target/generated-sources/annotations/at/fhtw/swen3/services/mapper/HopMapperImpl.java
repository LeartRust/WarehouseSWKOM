package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.Hop;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-26T15:40:28+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class HopMapperImpl implements HopMapper {

    @Override
    public Hop from(Hop hop) {
        if ( hop == null ) {
            return null;
        }

        Hop hop1 = new Hop();

        hop1.setHopType( hop.getHopType() );
        hop1.setCode( hop.getCode() );
        hop1.setDescription( hop.getDescription() );
        hop1.setProcessingDelayMins( hop.getProcessingDelayMins() );
        hop1.setLocationName( hop.getLocationName() );
        hop1.setLocationCoordinates( hop.getLocationCoordinates() );

        return hop1;
    }
}
