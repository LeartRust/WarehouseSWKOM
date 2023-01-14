package at.fhtw.swen3.persistence.entities;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Entity
@Table(name="warehouse_allOf_nextHops")
public class WarehouseNextHopsEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(nullable = false)
    private int id;
    //@Column
    private Integer traveltimeMins;
    @OneToOne
    @NotNull(message ="hop cannot be NULL")
    private HopEntity hop;
}
