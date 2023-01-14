package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Entity
@Table(name="warehouses")
public class WarehouseEntity extends HopEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(nullable = false)
    private int id;
    //@Column
    private Integer level;
    @OneToMany
    @NotNull(message ="nextHops cannot be NULL")
    private List<WarehouseNextHopsEntity> nextHops;
}
