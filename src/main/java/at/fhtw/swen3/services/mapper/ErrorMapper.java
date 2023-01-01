package at.fhtw.swen3.services.mapper;


import at.fhtw.swen3.services.dto.Error;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ErrorMapper {
    @Mapping(source = "error.errorMessage", target = "errorMessage")
    Error from(Error error);
}
