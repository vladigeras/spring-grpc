package ru.vladigeras.springgrpc.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCountryDto {

    private final String title;
}
