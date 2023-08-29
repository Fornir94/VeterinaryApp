package pl.gr.veterinaryapp.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetTime;
import java.util.List;

@Data
@Entity
@Table(name = "vets")
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String photoUrl;
    @NotNull
    private OffsetTime workStartTime;
    @NotNull
    private OffsetTime workEndTime;
}
