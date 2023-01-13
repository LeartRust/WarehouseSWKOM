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

        HopArrivalEntity hop = new HopArrivalEntity();
        hop.setDateTime(OffsetDateTime.now());

        HopArrivalEntity hop2 = new HopArrivalEntity();
        hop2.setDateTime(OffsetDateTime.now());

        HopEntity hopEnt = new HopEntity();
        hopEnt.setHopType("warehouse");
        hopEnt.setCode("ABCD1234");
        hopEnt.setDescription("test description");
        hopEnt.setProcessingDelayMins(5);
        hopEnt.setLocationName("Austria");
        //TODO
        GeoCoordinateEntity geoEnt = new GeoCoordinateEntity();
        //geoEnt.setId(2);
        geoEnt.setLat(2323.0);
        geoEnt.setLon(441.2);
        hopEnt.setLocationCoordinates(geoEnt);

        HopEntity hopEnt2 = new HopEntity();
        hopEnt2.setHopType("truck");
        hopEnt2.setCode("BACD4444");
        hopEnt2.setDescription("test description 2");
        hopEnt2.setProcessingDelayMins(7);
        hopEnt2.setLocationName("Italy");
        hopEnt2.setLocationCoordinates(geoEnt);

        geoCoordinateRepository.save(geoEnt);

        hopRepository.save(hopEnt);
        hopRepository.save(hopEnt2);

        List<HopArrivalEntity> visitedHops = new ArrayList<>();
        visitedHops.add(hop);
        visitedHops.add(hop2);
        parcelEntity.setFutureHops(visitedHops);
        parcelEntity.setVisitedHops(visitedHops);


        recipientRepository.save(parcelEntity.getRecipient());
        recipientRepository.save(parcelEntity.getSender());
        parcelRepository.save(parcelEntity);
        assertEquals(parcelRepository.findById(parcelEntity.getId()).get().getTrackingId(), parcelEntity.getTrackingId());


    }

}
