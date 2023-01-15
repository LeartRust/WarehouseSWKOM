package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-15T22:59:15+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class WarehouseMapperImpl implements WarehouseMapper {

    private final WarehouseNextHopsMapper warehouseNextHopsMapper = WarehouseNextHopsMapper.INSTANCE;
    private final GeoCoordinateMapper geoCoordinateMapper = GeoCoordinateMapper.INSTANCE;

    @Override
    public List<WarehouseEntity> WarehouseDtoListToWarehouseEntityList(List<Warehouse> warehouseDtoList) {
        if ( warehouseDtoList == null ) {
            return null;
        }

        List<WarehouseEntity> list = new ArrayList<WarehouseEntity>( warehouseDtoList.size() );
        for ( Warehouse warehouse : warehouseDtoList ) {
            list.add( WarehouseDtoToWarehouseEntity( warehouse ) );
        }

        return list;
    }

    @Override
    public List<Warehouse> WarehouseEntityListToWarehouseDtoList(List<WarehouseEntity> warehouseEntityList) {
        if ( warehouseEntityList == null ) {
            return null;
        }

        List<Warehouse> list = new ArrayList<Warehouse>( warehouseEntityList.size() );
        for ( WarehouseEntity warehouseEntity : warehouseEntityList ) {
            list.add( WarehouseEntityToWarehouseDto( warehouseEntity ) );
        }

        return list;
    }

    @Override
    public WarehouseEntity WarehouseDtoToWarehouseEntity(Warehouse warehouse) {
        if ( warehouse == null ) {
            return null;
        }

        WarehouseEntity warehouseEntity = new WarehouseEntity();

        warehouseEntity.setHopType( warehouse.getHopType() );
        warehouseEntity.setCode( warehouse.getCode() );
        warehouseEntity.setDescription( warehouse.getDescription() );
        warehouseEntity.setProcessingDelayMins( warehouse.getProcessingDelayMins() );
        warehouseEntity.setLocationName( warehouse.getLocationName() );
        warehouseEntity.setLocationCoordinates( geoCoordinateMapper.GeoCoordinateDtoToGeoCoordinateEntity( warehouse.getLocationCoordinates() ) );
        warehouseEntity.setLevel( warehouse.getLevel() );
        warehouseEntity.setNextHops( warehouseNextHopsListToWarehouseNextHopsEntityList( warehouse.getNextHops() ) );

        return warehouseEntity;
    }

    @Override
    public Warehouse WarehouseEntityToWarehouseDto(WarehouseEntity warehouseEntity) {
        if ( warehouseEntity == null ) {
            return null;
        }

        Warehouse warehouse = new Warehouse();

        warehouse.hopType( warehouseEntity.getHopType() );
        warehouse.code( warehouseEntity.getCode() );
        warehouse.description( warehouseEntity.getDescription() );
        warehouse.processingDelayMins( warehouseEntity.getProcessingDelayMins() );
        warehouse.locationName( warehouseEntity.getLocationName() );
        warehouse.locationCoordinates( geoCoordinateMapper.GeoCoordinateEntityToGeoCoordinateDto( warehouseEntity.getLocationCoordinates() ) );
        warehouse.setLevel( warehouseEntity.getLevel() );
        warehouse.setNextHops( warehouseNextHopsEntityListToWarehouseNextHopsList( warehouseEntity.getNextHops() ) );

        return warehouse;
    }

    @Override
    public Warehouse HopDtoToWarehouseDto(Hop Hop) {
        if ( Hop == null ) {
            return null;
        }

        Warehouse warehouse = new Warehouse();

        warehouse.hopType( Hop.getHopType() );
        warehouse.code( Hop.getCode() );
        warehouse.description( Hop.getDescription() );
        warehouse.processingDelayMins( Hop.getProcessingDelayMins() );
        warehouse.locationName( Hop.getLocationName() );
        warehouse.locationCoordinates( Hop.getLocationCoordinates() );

        return warehouse;
    }

    protected List<WarehouseNextHopsEntity> warehouseNextHopsListToWarehouseNextHopsEntityList(List<WarehouseNextHops> list) {
        if ( list == null ) {
            return null;
        }

        List<WarehouseNextHopsEntity> list1 = new ArrayList<WarehouseNextHopsEntity>( list.size() );
        for ( WarehouseNextHops warehouseNextHops : list ) {
            list1.add( warehouseNextHopsMapper.WarehouseNextHopsDtoToWarehouseNextHopsEntity( warehouseNextHops ) );
        }

        return list1;
    }

    protected List<WarehouseNextHops> warehouseNextHopsEntityListToWarehouseNextHopsList(List<WarehouseNextHopsEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<WarehouseNextHops> list1 = new ArrayList<WarehouseNextHops>( list.size() );
        for ( WarehouseNextHopsEntity warehouseNextHopsEntity : list ) {
            list1.add( warehouseNextHopsMapper.WarehouseNextHopsEntityToWarehouseNextHopsDto( warehouseNextHopsEntity ) );
        }

        return list1;
    }
}
