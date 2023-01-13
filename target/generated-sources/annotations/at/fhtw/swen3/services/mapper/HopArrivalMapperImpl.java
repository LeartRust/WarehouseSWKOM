package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-13T16:51:53+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class HopArrivalMapperImpl implements HopArrivalMapper {

    @Override
    public List<HopArrival> HopArrivalEntityListToHopArrivalDtoList(List<HopArrivalEntity> hopArrivalEntity) {
        if ( hopArrivalEntity == null ) {
            return null;
        }

        List<HopArrival> list = new ArrayList<HopArrival>( hopArrivalEntity.size() );
        for ( HopArrivalEntity hopArrivalEntity1 : hopArrivalEntity ) {
            list.add( hopArrivalEntityToHopArrival( hopArrivalEntity1 ) );
        }

        return list;
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
}
