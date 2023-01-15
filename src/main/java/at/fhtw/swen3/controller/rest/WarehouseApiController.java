package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.WarehouseApi;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-25T16:07:36.701220Z[Etc/UTC]")
@Controller
public class WarehouseApiController implements WarehouseApi {
    private static final org.slf4j.Logger loger = org.slf4j.LoggerFactory.getLogger(WarehouseApiController.class);

    private final NativeWebRequest request;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    public WarehouseApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Hop> getWarehouse( @Parameter(name = "code", description = "", required = true) @PathVariable("code") String code){
        //E. Get a Hop (Warehouse, Truck, TransferWarehouse) by code
        loger.info("Hop Code: " + code);
        Hop hop;
        try {
            hop = warehouseService.getWarehouse(code);
            log.info("API TEST: " + hop.getHopType());
            log.info("HopDto: " + hop);
        } catch (BLException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(hop, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> importWarehouses( @Parameter(name = "Warehouse", description = "", required = true) @Valid @RequestBody Warehouse warehouse){
        //C. Import a hierarchy of Hops (Warehouse, Truck, TransferWarehouse) objects.
        loger.info("Warehouse: " + warehouse);
        try {
            warehouseService.importWarehouses(warehouse);
        } catch (BLException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Warehouse> exportWarehouses( ){
        //D. Export a hierarchy of Hops (Warehouse, Truck, TransferWarehouse) objects .
        Warehouse warehouse;
        try {
            warehouse=warehouseService.exportWarehouses();
        } catch (BLException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(warehouse,HttpStatus.OK);
    }

}
