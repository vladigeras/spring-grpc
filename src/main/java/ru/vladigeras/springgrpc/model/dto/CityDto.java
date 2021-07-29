package ru.vladigeras.springgrpc.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityDto {

    private Long id;
    private String title;
    private CountryDto country;
}
