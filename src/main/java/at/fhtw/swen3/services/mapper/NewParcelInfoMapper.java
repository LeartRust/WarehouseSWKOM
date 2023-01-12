package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.NewParcelInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface NewParcelInfoMapper {

    NewParcelInfo from(NewParcelInfo newParcelInfo);
}
