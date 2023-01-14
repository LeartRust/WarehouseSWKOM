package at.fhtw.swen3.configuration;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.impl.WarehouseServiceImpl;
import at.fhtw.swen3.services.mapper.RecipientMapper;
import at.fhtw.swen3.services.mapper.RecipientMapperImpl;
import at.fhtw.swen3.services.validation.EntityValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
        "at.fhtw.swen3.persistence.repositories"
})
public class AppConfig {
    private final HopArrivalRepository hopArrivalRepository;

    public AppConfig(HopArrivalRepository hopArrivalRepository) {
        this.hopArrivalRepository = hopArrivalRepository;
    }

    @Bean
    //Validation
    public ParcelServiceImpl parcelServiceImpl(ParcelRepository parcelRepository, RecipientRepository recipientRepository,HopArrivalRepository hopArrivalRepository, HopRepository hopRepository, TransferwarehouseRepository transferwarehouseRepository, ErrorRepository errorRepository,EntityValidator validator) {
        return new ParcelServiceImpl(parcelRepository, recipientRepository, hopArrivalRepository, hopRepository, transferwarehouseRepository, errorRepository,validator);
    }

    @Bean
    //Validation
    public WarehouseServiceImpl warehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseNextHopsRepository warehouseNextHopsRepository, HopRepository hopRepository, GeoCoordinateRepository geoCoordinateRepository, ErrorRepository errorRepository,EntityValidator validator) {
        return new WarehouseServiceImpl(warehouseRepository, warehouseNextHopsRepository, geoCoordinateRepository, hopRepository, errorRepository,validator);
    }




}

