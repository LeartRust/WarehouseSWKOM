package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Entity
//@Table(name="truck")
public class TruckEntity extends HopEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(nullable = false)
    private int id;
    @Column(columnDefinition="text")
    private String regionGeoJson;
    //@Column
    private String numberPlate;
}
