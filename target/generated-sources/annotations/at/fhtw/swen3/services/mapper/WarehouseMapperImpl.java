package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-26T15:40:28+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class WarehouseMapperImpl implements WarehouseMapper {

    @Override
    public Warehouse from(Warehouse warehouse) {
        if ( warehouse == null ) {
            return null;
        }

        Warehouse warehouse1 = new Warehouse();

        warehouse1.setLevel( warehouse.getLevel() );
        List<WarehouseNextHops> list = warehouse.getNextHops();
        if ( list != null ) {
            warehouse1.setNextHops( new ArrayList<WarehouseNextHops>( list ) );
        }
        warehouse1.hopType( warehouse.getHopType() );
        warehouse1.code( warehouse.getCode() );
        warehouse1.description( warehouse.getDescription() );
        warehouse1.processingDelayMins( warehouse.getProcessingDelayMins() );
        warehouse1.locationName( warehouse.getLocationName() );
        warehouse1.locationCoordinates( warehouse.getLocationCoordinates() );

        return warehouse1;
    }
}
