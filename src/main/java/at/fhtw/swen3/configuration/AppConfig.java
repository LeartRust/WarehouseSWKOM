package at.fhtw.swen3.configuration;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.impl.WarehouseServiceImpl;
import at.fhtw.swen3.services.validation.EntityValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
        "at.fhtw.swen3.persistence.repositories"
})
public class AppConfig {

    @Bean
    //Validation
    public ParcelServiceImpl parcelServiceImpl(ParcelRepository parcelRepository, RecipientRepository recipientRepository, EntityValidator validator) {
        return new ParcelServiceImpl(parcelRepository, recipientRepository, validator);
    }

    @Bean
    //Validation
    public WarehouseServiceImpl warehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseNextHopsRepository warehouseNextHopsRepository, HopRepository hopRepository, GeoCoordinateRepository geoCoordinateRepository) {
        return new WarehouseServiceImpl(warehouseRepository, warehouseNextHopsRepository, geoCoordinateRepository, hopRepository);
    }




}

