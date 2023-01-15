package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.services.dto.Error;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-15T18:21:45+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class ErrorMapperImpl implements ErrorMapper {

    @Override
    public ErrorEntity ErrorDtoToErrorEntity(Error error) {
        if ( error == null ) {
            return null;
        }

        ErrorEntity.ErrorEntityBuilder errorEntity = ErrorEntity.builder();

        errorEntity.errorMessage( error.getErrorMessage() );

        return errorEntity.build();
    }

    @Override
    public Error ErrorEntityToErrorDto(ErrorEntity errorEntity) {
        if ( errorEntity == null ) {
            return null;
        }

        Error error = new Error();

        error.setErrorMessage( errorEntity.getErrorMessage() );

        return error;
    }
}
