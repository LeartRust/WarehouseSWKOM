package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.NewParcelInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface NewParcelInfoMapper {
    @Mapping(source = "newParcelInfo.trackingId", target = "trackingId")
    NewParcelInfo from(NewParcelInfo newParcelInfo);
}
