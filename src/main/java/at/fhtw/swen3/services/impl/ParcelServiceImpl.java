package at.fhtw.swen3.services.impl;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    //TODO DB funktionen + validation, tracking id generieren

    private final ParcelRepository parcelRepository;
    private final RecipientRepository recipientRepository;

}
