package at.fhtw.swen3.services.validation;

import at.fhtw.swen3.BaseTest;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
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
    public void parcelValidation() {
        log.info("TEST validationTest");
        ParcelEntity parcelEntity = getParcelEntityExample(TrackingInformation.StateEnum.PICKUP);
        validator.validate(parcelEntity);
    }


}
