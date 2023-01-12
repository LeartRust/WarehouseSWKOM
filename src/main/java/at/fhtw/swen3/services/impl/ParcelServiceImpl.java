package at.fhtw.swen3.services.impl;
import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.gps.service.impl.GeoCoordinates;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.validation.EntityValidator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.parser.Entity;

@Slf4j
@AllArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    //TODO DB funktionen + validation, tracking id generieren

    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private RecipientRepository recipientRepository;
    private final GeoEncodingService geoEncoding = new GeoCoordinates();

    private final EntityValidator validator;

    @Override
    public NewParcelInfo submitParcel(Parcel parcel) {
        log.info("START OF submitParcel");
        validator.validate(parcel);
        ParcelEntity entity = ParcelMapper.INSTANCE.ParcelDtoToParcelEntity(parcel);

        // TODO make ID unique
        // TODO add gps coordinates
        // generate tracking ID
        String trackingId = RandomStringUtils.randomAlphabetic(9).toUpperCase();

        entity.setTrackingId(trackingId);
        entity.setState(TrackingInformation.StateEnum.PICKUP);
/*
        // write to DB
        this.parcelRepository.save(entity);
        this.recipientRepository.save(entity.getSender());
        this.recipientRepository.save(entity.getRecipient());
*/
        NewParcelInfo newParcelInfo = new NewParcelInfo();

        newParcelInfo.setTrackingId(trackingId);
        log.info("Submit parcel '" + entity + "' with Tracking ID: " + trackingId);
        log.info("Weight: "+entity.getWeight());

        log.info("RECIPIENT Name: "+entity.getRecipient().getName());
        log.info("RECIPIENT Street: "+entity.getRecipient().getStreet());
        log.info("RECIPIENT Postalcode: "+entity.getRecipient().getPostalCode());
        log.info("RECIPIENT City: "+entity.getRecipient().getCity());
        log.info("RECIPIENT Country: "+entity.getRecipient().getCountry());

        log.info("SENDER Name: "+entity.getSender().getName());
        log.info("SENDER Street: "+entity.getSender().getStreet());
        log.info("SENDER Postalcode: "+entity.getSender().getPostalCode());
        log.info("SENDER City: "+entity.getSender().getCity());
        log.info("SENDER Country: "+entity.getSender().getCountry());
        //log.info("Coordinates for parcel: " + geoEncoding.getCoordinates(entity.getRecipient()););
        log.info("Future hops: " + entity.getFutureHops());

        return newParcelInfo;
    }

}
