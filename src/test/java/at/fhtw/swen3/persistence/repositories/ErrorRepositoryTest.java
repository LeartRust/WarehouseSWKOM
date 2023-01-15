package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.BaseTest;
import at.fhtw.swen3.persistence.entities.ErrorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ErrorRepositoryTest extends BaseTest {

    @Autowired
    ErrorRepository errorRepository;

    @Test
    void saveErrorInDb(){

        errorRepository.save(getErrorEntity());

    }

    @Test
    void deleteErrorInDb(){
        ErrorEntity error = getErrorEntity();
        errorRepository.save(error);
        errorRepository.delete(error);
    }


}