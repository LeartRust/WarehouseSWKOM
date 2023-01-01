package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.Transferwarehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TransferwarehouseMapper {
    @Mapping(source = "transferwarehouse.regionGeoJson", target = "regionGeoJson")
    @Mapping(source = "transferwarehouse.logisticsPartner", target = "logisticsPartner")
    @Mapping(source = "transferwarehouse.logisticsPartnerUrl", target = "logisticsPartnerUrl")
    Transferwarehouse from(Transferwarehouse transferwarehouse);
}
