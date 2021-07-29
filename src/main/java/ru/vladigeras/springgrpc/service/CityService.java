package ru.vladigeras.springgrpc.service;

import ru.vladigeras.springgrpc.model.dto.CityDto;
import ru.vladigeras.springgrpc.model.dto.CreateCityDto;

import java.util.List;

public interface CityService {

    Long save(CreateCityDto createCityDto);

    List<CityDto> find(String cityTitle, Long countryId);
}
