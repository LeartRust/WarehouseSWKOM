package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
public class ParcelEntity {
    //TODO ENUM wahrscheinlich falsch

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(nullable = false)
    private int id;
    //@Column
    private String trackingId;
    //@Column
    private TrackingInformation.StateEnum state;
    @OneToMany
    @NotNull(message ="visitedHops cannot be NULL")
    private List<HopArrivalEntity> visitedHops;
    @OneToMany
    @NotNull(message ="futureHops cannot be NULL")
    private List<HopArrivalEntity> futureHops;
    //@Column
    @DecimalMin("0.0")
    private Float weight;
    @OneToOne
    @NotNull(message ="recipient cannot be NULL")
    private RecipientEntity recipient;
    @OneToOne
    @NotNull(message ="sender cannot be NULL")
    private RecipientEntity sender;
}
