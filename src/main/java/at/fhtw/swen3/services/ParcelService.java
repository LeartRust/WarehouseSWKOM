package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import lombok.RequiredArgsConstructor;


public interface ParcelService {
    public NewParcelInfo submitParcel(Parcel parcel);
}
