package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.BaseTest;
import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@SpringBootTest
public class EntityValidationTest extends BaseTest {
    @Autowired
    EntityValidator validator;

    @Test
    void parcelValidation() {
        log.info("TEST validationTest");
        ParcelEntity parcelEntity = getParcelEntityExample(TrackingInformation.StateEnum.PICKUP);
        validator.validate(parcelEntity);
    }

    @Test
    void recipientValidation(){
        RecipientEntity recipientEntity = getRecipientEntity();
        validator.validate(recipientEntity);
    }

    @Test
    void warehouseValidation(){
        WarehouseEntity warehouseEntity = getWarehouseEntity();
        validator.validate(warehouseEntity);
    }
    @Test
    void hopValidation(){
        HopEntity hopEntity = getHopEntity();
        validator.validate(hopEntity);
    }
    @Test
    void transferWarehouseValidation(){
        TransferwarehouseEntity transferwarehouseEntity = getTransferWarehouse();
        validator.validate(transferwarehouseEntity);
    }

    @Test
    void truckValidation(){
        TruckEntity truck = getTruckEntity();
        validator.validate(truck);
    }


}
