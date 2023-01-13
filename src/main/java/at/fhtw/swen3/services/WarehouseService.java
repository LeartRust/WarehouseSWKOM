package at.fhtw.swen3.services;


import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;

public interface WarehouseService {

    //void importWarehouses(Warehouse warehouse) throws BLException;

    Hop getWarehouse(String code) throws BLException;
}
