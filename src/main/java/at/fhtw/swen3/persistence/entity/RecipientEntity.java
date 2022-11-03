package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@Entity
public class RecipientEntity {
    //TODO Validation Patterns überprüfen und ausbessern/ ONLY IF AUSTRIA?

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(nullable = false)
    private int id;
    //@Column
    @Pattern(regexp = "^[A-Z]{1}[A-Za-z-]")
    private String name;
    //@Column
    @Pattern(regexp = "^[A-Z]{1}[A-Za-z0-9\\/\\s]")
    private String street;
    //@Column
    @Pattern(regexp = "^\\b(A-)[0-9]{4}")
    private String postalCode;
    //@Column
    @Pattern(regexp = "^[A-Z]{1}[A-Za-z-]")
    private String city;
    //@Column
    private String country;
}
