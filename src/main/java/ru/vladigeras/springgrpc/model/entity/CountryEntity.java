package ru.vladigeras.springgrpc.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;

@Setter
@Getter
@Entity
@Table(name = "countries")
public class CountryEntity extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 7790799404063998843L;

    @Column(name = "title")
    private String title;
}
