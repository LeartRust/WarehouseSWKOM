package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.WarehouseNextHops;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-12T18:57:56+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class WarehouseNextHopsMapperImpl implements WarehouseNextHopsMapper {

    @Override
    public WarehouseNextHops from(WarehouseNextHops warehouseNextHops) {
        if ( warehouseNextHops == null ) {
            return null;
        }

        WarehouseNextHops warehouseNextHops1 = new WarehouseNextHops();

        warehouseNextHops1.setTraveltimeMins( warehouseNextHops.getTraveltimeMins() );
        warehouseNextHops1.setHop( warehouseNextHops.getHop() );

        return warehouseNextHops1;
    }
}
