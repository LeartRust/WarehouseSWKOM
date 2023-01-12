package at.fhtw.swen3.controller.rest;


import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.validation.EntityValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-25T16:07:36.701220Z[Etc/UTC]")
@Controller
public class ParcelApiController implements ParcelApi {

    private final NativeWebRequest request;

    @Autowired
    public ParcelApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel) {
        log.info("submitParcel " + parcel);
        //final ParcelServiceImpl parcelServiceImpl = new ParcelServiceImpl(ParcelRepository parcelRepository, RecipientRepository recipientRepository, EntityValidator validator);
        //parcelServiceImpl.submitParcel(parcel);
        return ParcelApi.super.submitParcel(parcel);
    }

}
