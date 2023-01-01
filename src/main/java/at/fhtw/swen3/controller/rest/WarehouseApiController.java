package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.OpenApiGeneratorApplication;
import at.fhtw.swen3.controller.WarehouseApi;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Warehouse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-25T16:07:36.701220Z[Etc/UTC]")
@Controller
public class WarehouseApiController implements WarehouseApi {
    private static final org.slf4j.Logger loger = org.slf4j.LoggerFactory.getLogger(WarehouseApiController.class);

    private final NativeWebRequest request;

    @Autowired
    public WarehouseApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> importWarehouses(Warehouse warehouse){
        loger.info("importWarehouses " + warehouse);
        return WarehouseApi.super.importWarehouses(warehouse);
    }

}
