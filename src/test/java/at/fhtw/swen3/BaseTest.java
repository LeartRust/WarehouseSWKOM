package at.fhtw.swen3;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.TransferwarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Slf4j
@Transactional
@SpringBootTest
public class BaseTest {

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

    protected Parcel getParcelExample(){
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

    protected ParcelEntity getParcelEntityExample(TrackingInformation.StateEnum stateEnum){

        ParcelEntity parcelEntity = new ParcelEntity();
        parcelEntity.setState(stateEnum);

        RecipientEntity recipientEntity = new RecipientEntity();
        recipientEntity.setStreet("Großfeldsiedlung");
        recipientEntity.setCity("Vienna");
        recipientEntity.setPostalCode("A-1210");
        recipientEntity.setName("Leart");
        recipientEntity.setCountry("Austria");

        RecipientEntity senderEntity = new RecipientEntity();
        senderEntity.setStreet("Airport");
        senderEntity.setCity("Prïstina");
        senderEntity.setPostalCode("A-1321");
        senderEntity.setName("Marcel");
        senderEntity.setCountry("Kosovo");

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
        WarehouseEntity warehouseEntity = getWarehouseEntity();



        /*
        HopEntity hopEnt = new HopEntity();
        hopEnt.setHopType("warehouse");
        hopEnt.setCode("ABCD1234");
        hopEnt.setDescription("test description");
        hopEnt.setProcessingDelayMins(5);
        hopEnt.setLocationName("Austria");
         */
        //TODO
        TransferwarehouseEntity hopEnt2 = new TransferwarehouseEntity();
        hopEnt2.setHopType("transferwarehouse");
        hopEnt2.setCode("BACD4444");
        hopEnt2.setDescription("test description 2");
        hopEnt2.setProcessingDelayMins(7);
        hopEnt2.setLocationName("Italy");
        hopEnt2.setLocationCoordinates(warehouseEntity.getLocationCoordinates());
        hopEnt2.setLogisticsPartner("AWS Instanz");
        //TODO Add PartnerUrl from AWS swaggerUI
        //hopEnt2.setLogisticsPartnerUrl();

        geoCoordinateRepository.save(warehouseEntity.getLocationCoordinates());

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


    protected GeoCoordinateEntity getGeoCoordinateEntity(){

        GeoCoordinateEntity geoEnt = new GeoCoordinateEntity();
        //geoEnt.setId(2);
        geoEnt.setLat(48.2391664);
        geoEnt.setLon(16.3774409);
        return geoEnt;
    }

    protected ErrorEntity getErrorEntity(){
        ErrorEntity error = ErrorEntity.builder()
                .errorMessage("Test error message")
                .build();
        return error;
    }

    protected WarehouseEntity getWarehouseEntity(){
        WarehouseEntity warehouseEntity=new WarehouseEntity();
        warehouseEntity.setHopType("warehouse");
        warehouseEntity.setCode("ABCD1234");
        warehouseEntity.setDescription("test description");
        warehouseEntity.setProcessingDelayMins(5);
        warehouseEntity.setLocationName("Austria");
        warehouseEntity.setLevel(1);
        warehouseEntity.setLocationCoordinates(getGeoCoordinateEntity());
        List<WarehouseNextHopsEntity> warehouseNextHopsEntityList = new ArrayList<>();
        //warehouseNextHopsEntityList.add();
        warehouseEntity.setNextHops(warehouseNextHopsEntityList);
        return warehouseEntity;
    }

    protected RecipientEntity getRecipientEntity(){
        RecipientEntity recipientEntity = new RecipientEntity();
        recipientEntity.setStreet("Rathausplatz 1");
        recipientEntity.setCity("Vienna");
        recipientEntity.setPostalCode("A-1010");
        recipientEntity.setName("Leart");
        recipientEntity.setCountry("Austria");
        return recipientEntity;
    }

    protected HopEntity getHopEntity(){
        HopEntity hopeEntity = new HopEntity();
        hopeEntity.setHopType("Hop");
        hopeEntity.setCode("BACD4444");
        hopeEntity.setDescription("test description 2");
        hopeEntity.setProcessingDelayMins(5);
        hopeEntity.setLocationName("Italy");
        hopeEntity.setLocationCoordinates(getGeoCoordinateEntity());
        //hopeEntity.setLogisticsPartner("AWS Instanz");
        return hopeEntity;
    }

    protected TransferwarehouseEntity getTransferWarehouse(){
        TransferwarehouseEntity transferwarehouseEntity = new TransferwarehouseEntity();
        transferwarehouseEntity.setHopType("transferwarehouse");
        transferwarehouseEntity.setCode("BACD4444");
        transferwarehouseEntity.setDescription("test description 2");
        transferwarehouseEntity.setProcessingDelayMins(5);
        transferwarehouseEntity.setLocationName("Italy");
        transferwarehouseEntity.setLocationCoordinates(getGeoCoordinateEntity());
        transferwarehouseEntity.setLogisticsPartner("AWS Instanz");
        return transferwarehouseEntity;
    }

    protected TruckEntity getTruckEntity(){
        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setNumberPlate("W-121A4");
        truckEntity.setRegionGeoJson("Random String");
        truckEntity.setCode("BACD4444");
        truckEntity.setDescription("test truck description");
        truckEntity.setProcessingDelayMins(2);
        truckEntity.setLocationName("Italy");
        truckEntity.setLocationCoordinates(getGeoCoordinateEntity());
        return truckEntity;
    }
}
