package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {RecipientMapper.class})
public interface ParcelMapper {
    ParcelMapper INSTANCE = Mappers.getMapper(ParcelMapper.class);

    @Mapping(source = "parcel.weight", target = "weight")
    @Mapping(source = "parcel.recipient", target = "recipient")
    @Mapping(source = "parcel.sender", target = "sender")
    @Mapping(source = "newParcelInfo.trackingId", target = "trackingId")
    @Mapping(source = "trackingInformation.state", target = "state")
    @Mapping(source = "trackingInformation.visitedHops", target = "visitedHops")
    @Mapping(source = "trackingInformation.futureHops", target = "futureHops")
    ParcelEntity ParcelNewParcelInfoTrackingInformationDtoToParcelEntity(Parcel parcel, NewParcelInfo newParcelInfo, TrackingInformation trackingInformation);

    @Mapping(source = "trackingInformation.state", target = "state")
    @Mapping(source = "trackingInformation.visitedHops", target = "visitedHops")
    @Mapping(source = "trackingInformation.futureHops", target = "futureHops")
    ParcelEntity TrackingInformationDtoToParcelEntity(TrackingInformation trackingInformation);

    ParcelEntity ParcelDtoToParcelEntity(Parcel parcel);

    @Mapping(source = "newParcelInfo.trackingId", target = "trackingId")
    ParcelEntity NewParcelInfoDtoToParcelEntity(NewParcelInfo newParcelInfo);

    @Mapping(source = "parcel.weight", target = "weight")
    @Mapping(source = "parcel.recipient", target = "recipient")
    @Mapping(source = "parcel.sender", target = "sender")
    @Mapping(source = "newParcelInfo.trackingId", target = "trackingId")
    ParcelEntity ParcelNewParcelInfoDtoToParcelEntity(Parcel parcel, NewParcelInfo newParcelInfo);
}
