package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;


public interface ParcelService {
    NewParcelInfo submitParcel(Parcel parcel);

    NewParcelInfo transitionParcel(String trackingId, Parcel parcel) throws BLException;

    TrackingInformation trackParcel(String trackingId) throws BLException;

     void reportParcelDelivery(String trackingId) throws BLException;


}
