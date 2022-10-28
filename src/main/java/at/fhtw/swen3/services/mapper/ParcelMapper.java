package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.Parcel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ParcelMapper {
    @Mapping(source = "parcel.weight", target = "weight")
    @Mapping(source = "parcel.recipient", target = "recipient")
    @Mapping(source = "parcel.sender", target = "sender")
    Parcel from(Parcel parcel);
}
