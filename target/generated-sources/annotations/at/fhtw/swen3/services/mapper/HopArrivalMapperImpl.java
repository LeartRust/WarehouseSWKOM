package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-15T22:59:15+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class HopArrivalMapperImpl implements HopArrivalMapper {

    @Override
    public List<HopArrival> HopArrivalEntityListToHopArrivalDtoList(List<HopArrivalEntity> hopArrivalEntityList) {
        if ( hopArrivalEntityList == null ) {
            return null;
        }

        List<HopArrival> list = new ArrayList<HopArrival>( hopArrivalEntityList.size() );
        for ( HopArrivalEntity hopArrivalEntity : hopArrivalEntityList ) {
            list.add( HopArrivalEntityToHopArrivalDto( hopArrivalEntity ) );
        }

        return list;
    }

    @Override
    public HopArrival HopArrivalEntityToHopArrivalDto(HopArrivalEntity hopArrivalEntity) {
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
