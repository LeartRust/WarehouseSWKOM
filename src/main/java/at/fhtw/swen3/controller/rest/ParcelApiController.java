package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.Optional;

@Slf4j
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-25T16:07:36.701220Z[Etc/UTC]")
@Controller
public class ParcelApiController implements ParcelApi {

    private final NativeWebRequest request;

    @Autowired
    private ParcelService parcelService;

    @Autowired
    public ParcelApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(@Parameter(name = "Parcel", description = "", required = true) @Valid @RequestBody Parcel parcel) {
        //A. Submit a new parcel to the logistics service.
        log.info("submitParcel " + parcel);
        NewParcelInfo parcelInfo = parcelService.submitParcel(parcel);
        return new ResponseEntity<>(parcelInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NewParcelInfo> transitionParcel(@Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true) @PathVariable("trackingId") String trackingId,
                                                          @Parameter(name = "Parcel", description = "", required = true) @Valid @RequestBody Parcel parcel) {
        //B. Transfer an existing parcel from the service of a logistics partner
        log.info("transitionParcel " + parcel + " trackingId: " + trackingId);
        NewParcelInfo parcelInfo;
        try {
            parcelInfo = parcelService.transitionParcel(trackingId, parcel);
        }catch (BLException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(parcelInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TrackingInformation> trackParcel(@Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true) @PathVariable("trackingId") String trackingId){
        //H. Track a parcel
        TrackingInformation tInfo;
        try {
            tInfo = parcelService.trackParcel(trackingId);
        }catch (BLException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> reportParcelDelivery(@Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true) @PathVariable("trackingId") String trackingId){
        //G. Report Parcel delivery at final address.
        try {
           parcelService.reportParcelDelivery(trackingId);
        }catch (BLException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> reportParcelHop(@Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true) @PathVariable("trackingId") String trackingId,
                                                @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$") @Parameter(name = "code", description = "The Code of the hop (Warehouse or Truck).", required = true) @PathVariable("code") String code
    ){
        //F. Report Parcel arrival at hop
        try {
            parcelService.reportParcelHop(trackingId, code);
        }catch (BLException e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
