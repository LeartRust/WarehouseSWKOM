package validation;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

import static org.junit.jupiter.api.Assertions.fail;
@SpringBootTest
@Slf4j

public class ParcelEntitityValidation {

  /*      @Test
        public void validationTest_shouldBeOk() {
            log.info("TEST validationTest");
            final ParcelEntity parcel = new Author("Rudi", "Ratlos", LocalDate.of(1976, 06, 03));
            final Book lord_of_the_rings = new Book("Lord of the rings", rudi, 1000, "With the ring on the way", 99.0f);

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Book>> violations = validator.validate(lord_of_the_rings);
            for (ConstraintViolation<Book> violation : violations)
            {
                log.error(violation.getMessage());
                fail(violation.getMessage());
            }

        }

        @Test
        public void validationTest_shouldFail() {
            log.info("TEST validationTest");
            final Author rudi = new Author("Rudi", "Ratlos", LocalDate.of(1976, 06, 03));
            final Book lord_of_the_rings = new Book("Ich", rudi, 1000, "With the ring on the way", 99.0f);

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Book>> violations = validator.validate(lord_of_the_rings);
            for (ConstraintViolation<Book> violation : violations)
            {
                log.info(violation.getMessage());
                return;
            }
            fail("Validation should fail!");
        }
    }*/
}
