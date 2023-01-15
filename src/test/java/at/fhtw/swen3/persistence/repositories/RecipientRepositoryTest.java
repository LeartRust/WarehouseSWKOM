package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.BaseTest;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class RecipientRepositoryTest extends BaseTest {

    @Autowired
    RecipientRepository recipientRepository;

    @Test
    void saveRecipientEntityInDb(){
        RecipientEntity recipient = getRecipientEntity();
        recipientRepository.save(recipient);
    }

}