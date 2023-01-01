package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Entity
public class HopArrivalEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column(nullable = false)
    private int id;
    //@Column
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    private String code;
    //@Column
    private String description;
    //@Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message ="dateTime cannot be NULL")
    private OffsetDateTime dateTime;
}
