package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.Error;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-12T18:57:55+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class ErrorMapperImpl implements ErrorMapper {

    @Override
    public Error from(Error error) {
        if ( error == null ) {
            return null;
        }

        Error error1 = new Error();

        error1.setErrorMessage( error.getErrorMessage() );

        return error1;
    }
}
