package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.TrackingInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TrackingInformationMapper {
    @Mapping(source = "trackingInformation.state", target = "state")
    @Mapping(source = "trackingInformation.visitedHops", target = "visitedHops")
    @Mapping(source = "trackingInformation.futureHops", target = "futureHops")
    TrackingInformation from(TrackingInformation trackingInformation);
}
