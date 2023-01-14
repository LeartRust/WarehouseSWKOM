package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.service.impl.GeoCoordinates;
import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@Transactional
@SpringBootTest
class ParcelServiceImplTest {
    @Autowired
    private ParcelServiceImpl parcelService;
    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    TransferwarehouseRepository transferwarehouseRepository;

    @Test
    void submitParcel() {

        Parcel parcel = getParcelExample();
        log.info(String.valueOf(parcel));
        NewParcelInfo parcelInfo = parcelService.submitParcel(parcel);
        log.info(String.valueOf(parcelInfo));
    }

    @Test
    void transitionParcel() throws BLException {

        Parcel parcel = getParcelExample();
        log.info(String.valueOf(parcel));
        String trackingId = parcelService.getUniqueTrackingId();
        NewParcelInfo parcelInfo = parcelService.transitionParcel(trackingId, parcel);
        log.info(String.valueOf(parcelInfo));
    }

    @Test
    void trackParcel() throws BLException {

        ParcelEntity parcel = getParcelEntityExample(TrackingInformation.StateEnum.PICKUP);
        TrackingInformation trackingInfo = parcelService.trackParcel(parcel.getTrackingId());
        log.info(String.valueOf(trackingInfo));
        }

    @Test
    void reportParcelDelivery() throws BLException{
        ParcelEntity parcelEntity = getParcelEntityExample(TrackingInformation.StateEnum.PICKUP);
        parcelService.reportParcelDelivery(parcelEntity.getTrackingId());
        ParcelEntity newParcel = parcelRepository.findByTrackingId(parcelEntity.getTrackingId());
        assertTrue(newParcel.getState().equals(TrackingInformation.StateEnum.DELIVERED), "Parcel State is DELIVERED");
    }

    @Test
    void reportParcelHop() throws BLException{
       ParcelEntity parcelEntity = getParcelEntityExample(TrackingInformation.StateEnum.PICKUP);
       parcelService.reportParcelHop(parcelEntity.getTrackingId(),"ABCD1234" );

    }

    private Parcel getParcelExample(){
        Recipient recipient = new Recipient();
        recipient.setName("Leart");
        recipient.setStreet("Höchstädtplatz 6");
        recipient.setCity("Wien");
        recipient.setCountry("Austria");
        recipient.setPostalCode("A-1200");

        Recipient sender = new Recipient();
        sender.setName("Marcel");
        sender.setStreet("Franz Jonas Platz");
        sender.setCity("Wien");
        sender.setCountry("Austria");
        sender.setPostalCode("A-1210");

        Parcel parcel = new Parcel();
        parcel.setSender(sender);
        parcel.setRecipient(recipient);
        parcel.setWeight(2.3f);
        return parcel;
    }

    private ParcelEntity getParcelEntityExample(TrackingInformation.StateEnum stateEnum){

        ParcelEntity parcelEntity = new ParcelEntity();
        parcelEntity.setState(stateEnum);

        RecipientEntity recipientEntity = new RecipientEntity();
        recipientEntity.setStreet("Landstraße");
        recipientEntity.setCity("Vienna");
        recipientEntity.setPostalCode("A-1210");
        recipientEntity.setName("Leart");
        recipientEntity.setCountry("Austria");

        RecipientEntity senderEntity = new RecipientEntity();
        senderEntity.setStreet("Landstraße");
        senderEntity.setCity("Prïstina");
        senderEntity.setPostalCode("A-1321");
        senderEntity.setName("Marcel");
        senderEntity.setCountry("Egypt");

        parcelEntity.setSender(senderEntity);
        parcelEntity.setRecipient(recipientEntity);

        parcelEntity.setTrackingId(parcelService.getUniqueTrackingId());
        parcelEntity.setWeight(20f);


        ParcelEntity parcelEntity2 = new ParcelEntity();
        parcelEntity2.setState(TrackingInformation.StateEnum.DELIVERED);
        parcelEntity2.setSender(senderEntity);
        parcelEntity2.setRecipient(recipientEntity);
        parcelEntity2.setTrackingId(parcelService.getUniqueTrackingId());
        parcelEntity2.setWeight(34f);


        HopArrivalEntity hop = new HopArrivalEntity();
        hop.setDateTime(OffsetDateTime.now());
        hop.setCode("ABCD1234");

        HopArrivalEntity hop2 = new HopArrivalEntity();
        hop2.setDateTime(OffsetDateTime.now());
        hop2.setCode("BACD4444");

        HopArrivalEntity hop3 = new HopArrivalEntity();
        hop3.setDateTime(OffsetDateTime.now());
        hop3.setCode("ABCD1234");

        HopArrivalEntity hop4 = new HopArrivalEntity();
        hop4.setDateTime(OffsetDateTime.now());
        hop4.setCode("BACD4444");


        //TODO Create 1 of each WarehouseEntity, TruckEntity, TransferwarehouseEntity
        WarehouseEntity warehouseEntity=new WarehouseEntity();
        warehouseEntity.setHopType("warehouse");
        warehouseEntity.setCode("ABCD1234");
        warehouseEntity.setDescription("test description");
        warehouseEntity.setProcessingDelayMins(5);
        warehouseEntity.setLocationName("Austria");
        warehouseEntity.setLevel(1);

        List<WarehouseNextHopsEntity> warehouseNextHopsEntityList = new ArrayList<>();
        //warehouseNextHopsEntityList.add();
        warehouseEntity.setNextHops(warehouseNextHopsEntityList);

        /*
        HopEntity hopEnt = new HopEntity();
        hopEnt.setHopType("warehouse");
        hopEnt.setCode("ABCD1234");
        hopEnt.setDescription("test description");
        hopEnt.setProcessingDelayMins(5);
        hopEnt.setLocationName("Austria");
         */
        //TODO
        GeoCoordinateEntity geoEnt = new GeoCoordinateEntity();
        //geoEnt.setId(2);
        geoEnt.setLat(2323.0);
        geoEnt.setLon(441.2);

        warehouseEntity.setLocationCoordinates(geoEnt);

        TransferwarehouseEntity hopEnt2 = new TransferwarehouseEntity();
        hopEnt2.setHopType("transferwarehouse");
        hopEnt2.setCode("BACD4444");
        hopEnt2.setDescription("test description 2");
        hopEnt2.setProcessingDelayMins(7);
        hopEnt2.setLocationName("Italy");
        hopEnt2.setLocationCoordinates(geoEnt);
        hopEnt2.setLogisticsPartner("AWS Instanz");
        //TODO Add PartnerUrl from AWS swaggerUI
        //hopEnt2.setLogisticsPartnerUrl();

        geoCoordinateRepository.save(geoEnt);

        warehouseRepository.save(warehouseEntity);
        //hopRepository.save(warehouseEntity);
        transferwarehouseRepository.save(hopEnt2);

        List<HopArrivalEntity> visitedHops = new ArrayList<>();
        visitedHops.add(hop2);
        parcelEntity.setVisitedHops(visitedHops);

        List<HopArrivalEntity> futureHops = new ArrayList<>();
        futureHops.add(hop);
        parcelEntity.setFutureHops(futureHops);

        log.info(parcelEntity.getFutureHops().size() + " TESTTTTT");

        List<HopArrivalEntity> visitedHops2 = new ArrayList<>();
        visitedHops2.add(hop4);
        parcelEntity2.setVisitedHops(visitedHops2);

        List<HopArrivalEntity> futureHops2 = new ArrayList<>();
        futureHops2.add(hop3);
        parcelEntity2.setFutureHops(futureHops2);



        recipientRepository.save(parcelEntity.getRecipient());
        recipientRepository.save(parcelEntity.getSender());
        parcelRepository.save(parcelEntity);
        parcelRepository.save(parcelEntity2);
        assertEquals(parcelRepository.findById(parcelEntity.getId()).get().getTrackingId(), parcelEntity.getTrackingId());

        return parcelEntity;
    }
}