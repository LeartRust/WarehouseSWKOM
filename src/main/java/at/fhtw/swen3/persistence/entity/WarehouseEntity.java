package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
public class WarehouseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private int id;
    @Column
    private Integer level;
    @Column
    @OneToMany
    @NotNull(message ="nextHops cannot be NULL")
    private List<WarehouseNextHopsEntity> nextHops;
}
