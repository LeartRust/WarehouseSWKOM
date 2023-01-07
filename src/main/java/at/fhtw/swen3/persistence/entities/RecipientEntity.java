package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Entity
public class RecipientEntity {
    //TODO Validation Patterns überprüfen und ausbessern/ ONLY IF AUSTRIA?

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(nullable = false)
    private int id;
    //@Column
    //@Pattern(regexp = "^[A-Z]{1}[A-Za-z-]")
    private String name;
    //@Column
    //@Pattern(regexp = "^[A-Z]{1}[A-Za-z0-9\\/\\s]")
    private String street;
    //@Column
    //@Pattern(regexp = "^\\b(A-)[0-9]{4}")
    //TODO Regex laut sprint2
    //@Pattern(regexp = "", message = ("PostalCode 1Buchstabe + 4Ziffern"))
    private String postalCode;
    //@Column
    @Pattern(regexp = "^[A-Z]{1}[A-Za-z-]*", message = ("City needs to be only letters"))
    private String city;
    //@Column
    private String country;
}
