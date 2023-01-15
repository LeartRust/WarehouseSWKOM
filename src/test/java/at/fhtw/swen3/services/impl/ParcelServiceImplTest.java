package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.BaseTest;
import at.fhtw.swen3.gps.service.impl.GeoCoordinates;
import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.*;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.*;
import com.fasterxml.jackson.databind.ser.Serializers;
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
class ParcelServiceImplTest extends BaseTest {
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

}