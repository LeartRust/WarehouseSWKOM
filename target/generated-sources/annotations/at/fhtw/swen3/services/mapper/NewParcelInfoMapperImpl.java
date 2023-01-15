package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.NewParcelInfo;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-15T22:43:44+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
public class NewParcelInfoMapperImpl implements NewParcelInfoMapper {

    @Override
    public NewParcelInfo from(NewParcelInfo newParcelInfo) {
        if ( newParcelInfo == null ) {
            return null;
        }

        NewParcelInfo newParcelInfo1 = new NewParcelInfo();

        newParcelInfo1.setTrackingId( newParcelInfo.getTrackingId() );

        return newParcelInfo1;
    }
}
