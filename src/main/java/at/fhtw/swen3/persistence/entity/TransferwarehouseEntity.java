package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class TransferwarehouseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private int id;
    @Column
    private String regionGeoJson;
    @Column
    private String logisticsPartner;
    @Column
    private String logisticsPartnerUrl;
}
