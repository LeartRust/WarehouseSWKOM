package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
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
@SpringBootTest(classes= ParcelRepositoryTest.class)
public class ParcelRepositoryTest {
    /*
    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private RecipientRepository recipientRepository;
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
        List<HopArrivalEntity> visitedHops = new ArrayList<>();
        visitedHops.add(hop);
        parcelEntity.setFutureHops(visitedHops);
        parcelEntity.setVisitedHops(visitedHops);
        recipientRepository.save(parcelEntity.getRecipient());
        recipientRepository.save(parcelEntity.getSender());
        parcelRepository.save(parcelEntity);
        assertEquals(parcelRepository.findById(parcelEntity.getId()).get().getTrackingId(), parcelEntity.getTrackingId());


    }
     */
}
