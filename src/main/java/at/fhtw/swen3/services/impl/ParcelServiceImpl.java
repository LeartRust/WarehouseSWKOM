package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.gps.service.impl.GeoCoordinates;
import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.mapper.HopArrivalMapper;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.validation.EntityValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@AllArgsConstructor
public class ParcelServiceImpl implements ParcelService {
    @Autowired
    private ParcelRepository parcelRepository;
    @Autowired
    private RecipientRepository recipientRepository;
    @Autowired
    private HopArrivalRepository hopArrivalRepository;
    @Autowired
    private HopRepository hopRepository;
    @Autowired
    private TransferwarehouseRepository transferwarehouseRepository;

    @Autowired
    private final ErrorRepository errorRepository;

    private final GeoEncodingService geoEncoding = new GeoCoordinates();

    @Autowired
    private final EntityValidator validator;

    @Override
    public NewParcelInfo submitParcel(Parcel parcel) {
        //A. Submit a new parcel to the logistics service.
        //TODO Predict FutureHops

        log.info("START OF submitParcel");

        validator.validate(parcel);
        ParcelEntity entity = ParcelMapper.INSTANCE.ParcelDtoToParcelEntity(parcel);

        String trackingId = getUniqueTrackingId();

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
        try {
            log.info("Coordinates for parcel: " + geoEncoding.getCoordinates(entity.getRecipient()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        };
        log.info("Future hops: " + entity.getFutureHops());

        log.info("TRACKING ID: " + trackingId);

        return newParcelInfo;
    }


    @Override
    public NewParcelInfo transitionParcel(String trackingId, Parcel parcel) throws BLException {
        //B. Transfer an existing parcel from the service of a logistics partner
        //TODO Braucht diese Methode auch Predict FutureHops?

        log.info("START OF transitionParcel");
        validator.validate(parcel);
        ParcelEntity entity = ParcelMapper.INSTANCE.ParcelDtoToParcelEntity(parcel);

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
            try {
                log.info("Coordinates for parcel: " + geoEncoding.getCoordinates(entity.getRecipient()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            log.info("Future hops: " + entity.getFutureHops());

            log.info("TRACKING ID: " + trackingId);
            return newParcelInfo;
        } else {
            log.info("TrackingId already in use. TrackingId: " + trackingId);
            BLException exception= new BLException(1,"trackingId is not unique", null);
            errorRepository.save(exception.getErrorEntity());
            throw exception;
        }
    }

    @Override
    public TrackingInformation trackParcel(String trackingId) throws BLException {
        //H. Track a parcel

        validator.validate(trackingId);
        ParcelEntity parcel = parcelRepository.findByTrackingId(trackingId);
        if (parcel != null){
            TrackingInformation tInfo = new TrackingInformation();
            tInfo.setState(parcel.getState());
            tInfo.setFutureHops(HopArrivalMapper.INSTANCE.HopArrivalEntityListToHopArrivalDtoList(parcel.getFutureHops()));
            tInfo.setVisitedHops(HopArrivalMapper.INSTANCE.HopArrivalEntityListToHopArrivalDtoList(parcel.getVisitedHops()));

            return tInfo;
        }else{
            BLException exception= new BLException(2, "Parcel not found, check TrackingId", null);
            errorRepository.save(exception.getErrorEntity());
            throw exception;
        }
    }

    @Override
    public void reportParcelDelivery(String trackingId) throws BLException {
        //G. Report Parcel delivery at final address.

        validator.validate(trackingId);
        ParcelEntity parcel = parcelRepository.findByTrackingId(trackingId);
        if (parcel != null){
            parcel.setState(TrackingInformation.StateEnum.DELIVERED);
            parcelRepository.save(parcel);
        }else{
            BLException exception= new BLException(2, "Parcel not found, check TrackingId", null);
            errorRepository.save(exception.getErrorEntity());
            throw exception;
        }
    }

    @Override
    public void reportParcelHop(String trackingId, String code) throws BLException {
        //F. Report Parcel arrival at hop

        log.info("START reportParcelHop");
        //Search for Parcel with trackingId
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        List< HopArrivalEntity> ListForId = parcelEntity.getFutureHops();
        Integer arrivalId=null;
        //Search for HopArrivalEntity from FutureHops List with Code
        for (HopArrivalEntity l : ListForId) {
            if(l.getCode().equals(code)){
                arrivalId=l.getId();
            }
        }
        log.info("TEST ID: "+arrivalId);
        //Get HopArrivalEntity with arrivalId from Database
        HopArrivalEntity hopArrivalEntity = hopArrivalRepository.findByCodeAndId(code, arrivalId);
        HopEntity hopEntity = hopRepository.findByCode(code);
        log.info("Hop that is reported: " + hopArrivalEntity);
        if (parcelEntity != null && hopArrivalEntity != null){

            List< HopArrivalEntity> futureHopsList = parcelEntity.getFutureHops();
            log.info("futureHopsList before: "+futureHopsList);

            futureHopsList.remove(hopArrivalEntity);
            log.info("futureHopsList after: "+futureHopsList);

            List< HopArrivalEntity> visitedHopsList = parcelEntity.getVisitedHops();
            log.info("visitedHopsList before: "+visitedHopsList);
            visitedHopsList.add(hopArrivalEntity);
            log.info("visitedHopsList after: "+visitedHopsList);

            switch(hopEntity.getHopType()){
                case "warehouse":
                    log.info("HopType ist warehouse");
                    parcelEntity.setState(TrackingInformation.StateEnum.INTRANSPORT);
                    break;
                case "truck":
                    log.info("HopType ist truck");
                    parcelEntity.setState(TrackingInformation.StateEnum.INTRUCKDELIVERY);
                    break;
                case "transferwarehouse":
                    //TODO Verknüpfung mit der Url einbauen
                    log.info("HopType ist transferwarehouse");
                    TransferwarehouseEntity transferwarehouseEntity = transferwarehouseRepository.findByCode(code);
                    log.info("partnerUrl: "+ transferwarehouseEntity.getLogisticsPartnerUrl());
                    //POST https://<partnerUrl>/parcel/<trackingId>
                    //http://springbootdeploy-env.eba-4vwbis37.us-east-1.elasticbeanstalk.com/swagger-ui/index.html#/parcel/<trackingId>
                    parcelEntity.setState(TrackingInformation.StateEnum.TRANSFERRED);
                    break;
                default:
                    log.info("HopType ist ungültig");
                    break;
            }
            parcelRepository.save(parcelEntity);
        }else{
            BLException exception= new BLException(2, "Parcel and/or Hop not found, check TrackingId/Code", null);
            errorRepository.save(exception.getErrorEntity());
            throw exception;
        }
    }


    public String getUniqueTrackingId(){
        //Creates Unique Tracking Id
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
        return trackingId;
    }

}
