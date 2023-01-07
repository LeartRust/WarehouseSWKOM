package at.fhtw.swen3;

import com.fasterxml.jackson.databind.Module;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"org.openapitools","at.fhtw.swen3.services", "at.fhtw.swen3.controller.rest" , "at.fhtw.swen3.configuration", "at.fhtw.swen3.persistence.repositories"})

public class OpenApiGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenApiGeneratorApplication.class, args);
        log.info("LOMBOK LOG TEST");
    }

    @Bean
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}