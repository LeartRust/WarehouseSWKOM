package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="geoCoordinate")
public class GeoCoordinateEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(nullable = false)
    private int id;
    //@Column
    private Double lat;
    //@Column
    private Double lon;
}
