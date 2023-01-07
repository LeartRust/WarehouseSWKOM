package at.fhtw.swen3.configuration;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.impl.WarehouseServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    //Validation
    public ParcelServiceImpl parcelServiceImpl(ParcelRepository parcelRepository, RecipientRepository recipientRepository) {
        return new ParcelServiceImpl(parcelRepository, recipientRepository);
    }

    @Bean
    //Validation
    public WarehouseServiceImpl warehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseNextHopsRepository warehouseNextHopsRepository, HopRepository hopRepository, GeoCoordinateRepository geoCoordinateRepository) {
        return new WarehouseServiceImpl(warehouseRepository, warehouseNextHopsRepository, geoCoordinateRepository, hopRepository);
    }



}

