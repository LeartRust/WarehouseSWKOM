package at.fhtw.swen3.services.impl;
import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.gps.service.impl.GeoCoordinates;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.HopArrivalMapper;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.validation.EntityValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.parser.Entity;
import java.util.Random;

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

        // TODO add gps coordinates
        // generate tracking ID
        String trackingId;
        String trackingIdCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        do {
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 9) { // length of the random string.
                int index = (int) (rnd.nextFloat() * trackingIdCHARS.length());
                salt.append(trackingIdCHARS.charAt(index));
            }
            trackingId = salt.toString();
        }while (parcelRepository.findByTrackingId(trackingId) != null);


        entity.setTrackingId(trackingId);
        entity.setState(TrackingInformation.StateEnum.PICKUP);

        // write to DB
        this.recipientRepository.save(entity.getSender());
        this.recipientRepository.save(entity.getRecipient());
        this.parcelRepository.save(entity);

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

        log.info("TRACKING ID: " + trackingId);

        return newParcelInfo;


    }


    @Override
    public NewParcelInfo transitionParcel(String trackingId, Parcel parcel) throws BLException {
        log.info("START OF transitionParcel");
        validator.validate(parcel);
        ParcelEntity entity = ParcelMapper.INSTANCE.ParcelDtoToParcelEntity(parcel);


        // TODO add gps coordinates
        // generate tracking ID

        if (parcelRepository.findByTrackingId(trackingId) == null){
            entity.setTrackingId(trackingId);
            entity.setState(TrackingInformation.StateEnum.PICKUP);

            // write to DB
            this.recipientRepository.save(entity.getSender());
            this.recipientRepository.save(entity.getRecipient());
            this.parcelRepository.save(entity);

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

            log.info("TRACKING ID: " + trackingId);
            return newParcelInfo;
        } else {
            //TODO Exception Handling
            log.info("TrackingId already in use. TrackingId: " + trackingId);
            throw new BLException(1,"trackingId is not unique", null);
        }
    }

    @Override
    public TrackingInformation trackParcel(String trackingId) throws BLException {

        ParcelEntity parcel = parcelRepository.findByTrackingId(trackingId);

        if (parcel != null){

            TrackingInformation tInfo = new TrackingInformation();
            tInfo.setState(parcel.getState());
            tInfo.setFutureHops(HopArrivalMapper.INSTANCE.HopArrivalEntityListToHopArrivalDtoList(parcel.getFutureHops()));
            tInfo.setVisitedHops(HopArrivalMapper.INSTANCE.HopArrivalEntityListToHopArrivalDtoList(parcel.getVisitedHops()));
            return tInfo;
        }else{
            throw new BLException(2, "Parcel not found, check TrackingId", null);
        }
    }

    @Override
    public void reportParcelDelivery(String trackingId) throws BLException {
        ParcelEntity parcel = parcelRepository.findByTrackingId(trackingId);

        if (parcel != null){
            parcel.setState(TrackingInformation.StateEnum.DELIVERED);
            parcelRepository.save(parcel);
        }else{
            throw new BLException(2, "Parcel not found, check TrackingId", null);
        }
    }


}
