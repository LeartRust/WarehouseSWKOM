package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="parcel")
public class ParcelEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(nullable = false)
    private int id;
    //@Column
    @Pattern(regexp = "^[A-Z0-9]{9}$")
    private String trackingId;
    //@Column
    private TrackingInformation.StateEnum state;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    //@NotNull(message ="visitedHops cannot be NULL")
    private List<HopArrivalEntity> visitedHops;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    //@NotNull(message ="futureHops cannot be NULL")
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
