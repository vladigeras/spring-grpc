package ru.vladigeras.springgrpc.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCityDto {

    private final String title;
    private final Long countryId;
}
