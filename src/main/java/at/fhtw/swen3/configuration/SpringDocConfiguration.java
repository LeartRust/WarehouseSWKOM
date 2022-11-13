package at.fhtw.swen3.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SpringDocConfiguration {

    @Bean
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Parcel Logistics Service")
                                .description("No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)")
                                .contact(
                                        new Contact()
                                                .name("SKS")
                                                .url("http://www.technikum-wien.at/")
                                )
                                .version("1.22.1")
                )
        ;
    }
}