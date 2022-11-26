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
@ComponentScan(basePackages = {"org.openapitools", "at.fhtw.swen3.services" , "at.fhtw.swen3.configuration"})
public class OpenApiGeneratorApplication {
    private static final org.slf4j.Logger loger = org.slf4j.LoggerFactory.getLogger(OpenApiGeneratorApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(OpenApiGeneratorApplication.class, args);
        loger.info("testing logging with lombok");
    }

    @Bean
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}