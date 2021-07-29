package ru.vladigeras.springgrpc.service;

import ru.vladigeras.springgrpc.model.dto.CreateCountryDto;

public interface CountryService {

    Long save(CreateCountryDto createCountryDto);
}
