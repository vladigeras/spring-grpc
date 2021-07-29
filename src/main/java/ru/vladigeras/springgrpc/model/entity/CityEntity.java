package ru.vladigeras.springgrpc.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;

@Setter
@Getter
@Entity
@Table(name = "cities")
public class CityEntity extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 863882004509626378L;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private CountryEntity country;
}
