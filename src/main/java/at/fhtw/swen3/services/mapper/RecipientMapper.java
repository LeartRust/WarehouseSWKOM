package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RecipientMapper {
    /*
    @Mapping(source = "recipient.name", target = "name")
    @Mapping(source = "recipient.street", target = "street")
    @Mapping(source = "recipient.postalCode", target = "postalCode")
    @Mapping(source = "recipient.city", target = "city")
    @Mapping(source = "recipient.country", target = "country")
    */
    RecipientEntity RecipientDtoToRecipientEntity(Recipient recipient);

}
