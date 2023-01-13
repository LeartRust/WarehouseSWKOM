package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class ParcelRepositoryTest {

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private HopRepository hopRepository;

    @Autowired
    private GeoCoordinateRepository geoCoordinateRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private TransferwarehouseRepository transferwarehouseRepository;

    @Test
    void saveAndDeleteParcelEntity() {

        ParcelEntity parcelEntity = new ParcelEntity();
        parcelEntity.setState(TrackingInformation.StateEnum.DELIVERED);

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

        parcelEntity.setTrackingId("PYJRB4HZ6");
        parcelEntity.setWeight(34f);


        ParcelEntity parcelEntity2 = new ParcelEntity();
        parcelEntity2.setState(TrackingInformation.StateEnum.DELIVERED);
        parcelEntity2.setSender(senderEntity);
        parcelEntity2.setRecipient(recipientEntity);
        parcelEntity2.setTrackingId("STDRB4HZ7");
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


    }

}
