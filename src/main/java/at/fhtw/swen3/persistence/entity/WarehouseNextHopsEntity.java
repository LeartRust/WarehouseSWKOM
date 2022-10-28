package at.fhtw.swen3.persistence.entity;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class WarehouseNextHopsEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private int id;
    @Column
    private Integer traveltimeMins;
    @Column
    @OneToOne
    @NotNull(message ="hop cannot be NULL")
    private HopEntity hop;
}
