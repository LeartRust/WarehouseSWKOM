package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="recipient")
public class RecipientEntity {
    //TODO Validation Patterns überprüfen und ausbessern/ ONLY IF AUSTRIA?

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(nullable = false)
    private int id;
    //@Column
    @Pattern(regexp = "^[A-Z]{1}[A-Za-z-]*")
    private String name;
    //@Column
    @Pattern(regexp = "^[A-Z]{1}[A-Za-z0-9äöüß\\/\\s]*")
    private String street;
    //@Column
    @Pattern(regexp = "^[A-Z]-[0-9]{4}")
    private String postalCode;
    //@Column
    @Pattern(regexp = "^[A-Z]{1}[A-Za-z-ï]*")
    private String city;
    //@Column
    private String country;
}
