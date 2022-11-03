package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
public class HopEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(nullable = false)
    private int id;
    //@Column
    private String hopType;
    //@Column
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    private String code;
    //@Column
    private String description;
    //@Column
    private Integer processingDelayMins;
    //@Column
    private String locationName;
    @OneToOne
    @NotNull(message ="locationCoordinates cannot be NULL")
    private GeoCoordinateEntity locationCoordinates;
}
