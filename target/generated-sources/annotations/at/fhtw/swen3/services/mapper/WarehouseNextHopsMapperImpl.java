package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-15T22:43:44+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class WarehouseNextHopsMapperImpl implements WarehouseNextHopsMapper {

    private final HopMapper hopMapper = HopMapper.INSTANCE;

    @Override
    public WarehouseNextHopsEntity WarehouseNextHopsDtoToWarehouseNextHopsEntity(WarehouseNextHops warehouseNextHops) {
        if ( warehouseNextHops == null ) {
            return null;
        }

        WarehouseNextHopsEntity warehouseNextHopsEntity = new WarehouseNextHopsEntity();

        warehouseNextHopsEntity.setTraveltimeMins( warehouseNextHops.getTraveltimeMins() );
        warehouseNextHopsEntity.setHop( hopMapper.HopDtoToHopEntity( warehouseNextHops.getHop() ) );

        return warehouseNextHopsEntity;
    }

    @Override
    public WarehouseNextHops WarehouseNextHopsEntityToWarehouseNextHopsDto(WarehouseNextHopsEntity warehouseNextHopsEntity) {
        if ( warehouseNextHopsEntity == null ) {
            return null;
        }

        WarehouseNextHops warehouseNextHops = new WarehouseNextHops();

        warehouseNextHops.setTraveltimeMins( warehouseNextHopsEntity.getTraveltimeMins() );
        warehouseNextHops.setHop( hopMapper.HopEntityToHopDto( warehouseNextHopsEntity.getHop() ) );

        return warehouseNextHops;
    }
}
